package edu.nclg.netctoss.service.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.BillDetailDao;
import edu.nclg.netctoss.dao.BillItemDao;
import edu.nclg.netctoss.entity.BillDetail;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BillDetailService;

/**
 * 账单详单业务层实现类
 * @author pjz
 *
 */
@Service("billDetailService")
public class BillDetailServiceImpl implements BillDetailService {

	private static final long serialVersionUID = 1605023002258627721L;
	
	@Resource
	private BillDetailDao billDetailDao;
	@Resource
	private BillItemDao billItemDao;

	//查询所有账单详单
	@Override
	@Transactional
	public List<BillDetail> queryAll() throws Exception {
		List<BillDetail> list = billDetailDao.queryAll();
		return list;
	}
	
	//根据服务器IP查询所有账单详单
	@Override
	@Transactional
	public List<BillDetail> queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("服务器IP不能为空");
		}
		List<BillDetail> list = billDetailDao.queryByUnixHost(unixHost);
		return list;
	}
	
	//根据服务器IP查询指定年份月份的账单详单
	@Override
	@Transactional
	public List<BillDetail> queryByUnixHost(int billItemId) throws Exception {
		
		//根据账单明细ID获得：服务器IP和时间（年份月份）
		BillItem billItem = billItemDao.queryByBillItemId(billItemId);
		
		List<BillDetail> list = billDetailDao.queryByUnixHost(billItem.getUnixHost());	//根据服务器IP获得所有账单详单信息
		//获得年份月份信息
		Date date = billItem.getDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		//获得指定年份月份账单详单信息
		List<BillDetail> dateList = new ArrayList<BillDetail>();
		for(BillDetail b:list){
			calendar.setTime(b.getOutTime());
			if(year==calendar.get(Calendar.YEAR) && month==calendar.get(Calendar.MONTH)){
				dateList.add(b);
			}
			
		}
		
		return dateList;
	}

	//添加账单详单(业务账户登录时调用，登录时记录登录时间，退出时记录退出时间，最后存入账单详单表中)
	@Override
	@Transactional
	public void addBillDetail(BillDetail billDetail) throws Exception {
		if(billDetail == null){
			throw new ServiceException("账单详单不能为空");
		}
		billDetailDao.addBillDetail(billDetail);
	}
	
	
}

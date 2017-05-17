package edu.nclg.netctoss.service.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.AccountDao;
import edu.nclg.netctoss.dao.BillItemDao;
import edu.nclg.netctoss.entity.BillItem;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.BillItemService;

@Service("billItemService")
public class BillItemServiceImpl implements BillItemService {
	
	private static final long serialVersionUID = 615406081219826155L;
	
	@Resource
	private BillItemDao billItemDao;
	@Resource
	private AccountDao accountDao;
	
	//查询所有账单详单信息
	@Override
	@Transactional
	public List<BillItem> queryAll() throws Exception {
		List<BillItem> list = billItemDao.queryAll();
		return list;
	}

	//账单管理页面点击“明细”时，调用本方法
	//根据账务账号查询指定月份所有业务账号的账单详单信息
	//根据账务账号，得到所有业务账号的账单详单信息，在获得指定月份的所有业务账号的账单详单信息
	@Override
	@Transactional
	public List<BillItem> queryByAccountName(String accountName, int year, int month) throws Exception {
		if(accountName==null || accountName.trim().isEmpty()){
			throw new ServiceException("账务账号不能为空");
		}
		
		//根据账务账号登录名称得到账务账号ID
		int accountId = accountDao.queryByLoginName(accountName).getAccountId();
		//根据账务账号ID获得所有业务账号账单明细
		List<BillItem> list = billItemDao.queryByAccountId(accountId);
		//获得指定年份月份 所有业务账号账单明细
		List<BillItem> businessList = new ArrayList<BillItem>();
		for(BillItem b:list){
			
			Calendar c = Calendar.getInstance();
			c.setTime(b.getDate());
			
			if(year==c.get(Calendar.YEAR) && month==c.get(Calendar.MONTH)+1){
				businessList.add(b);
			}
			
		}
		System.out.println(businessList);
		return businessList;
	}
	
	//根据ip查询所有账单细节信息
	@Override
	@Transactional
	public List<BillItem> queryByUnixHost(String unixHost) throws Exception {
		if(unixHost==null || unixHost.trim().isEmpty()){
			throw new ServiceException("IP不能为空");
		}
		List<BillItem> list = billItemDao.queryByUnixHost(unixHost);
		return list;
	}
	
	//添加账单细节信息
	@Override
	@Transactional
	public void addBillItem(BillItem billItem) throws Exception {
		if(billItem==null){
			throw new ServiceException("账单细节信息不能为空");
		}
		billItemDao.addBillItem(billItem);
		
	}

	
	
	

}

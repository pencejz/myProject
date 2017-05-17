package edu.nclg.netctoss.service.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nclg.netctoss.dao.CostDao;
import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.exception.ServiceException;
import edu.nclg.netctoss.service.CostService;

/**
 * �ʷ���Ϣҵ���
 * @author pjz
 * 
 * @Service ����Ϊҵ���
 * @Resource Ĭ�ϰ�������ע��
 * @Transactional ����֧��
 *
 */
@Service("costService")
public class CostServiceImpl implements CostService{

	private static final long serialVersionUID = 7601785074329607151L;

	@Resource
	private CostDao costDao;
	
	//��ѯ�����ʷ���Ϣ
	@Override
	@Transactional
	public List<Cost> queryAll() {
		List<Cost> list = costDao.queryAll();
		return list;
	}

	//�����ʷ�ID��ѯ�ʷ���Ϣ
	@Override
	@Transactional
	public Cost queryByCostId(int costId) throws Exception {
		Cost cost = costDao.queryByCostId(costId);
		return cost;
	}

	//����ʷ���Ϣ
	@Override
	@Transactional
	public void addCost(Cost cost) throws Exception {
		if(cost==null){
			throw new ServiceException("�ʷ���Ϣ����Ϊ��");
		}
		
		int status = NO_USE_STATUS;
		Date createTime = new Date();
		cost.setStatus(status);
		cost.setCreateTime(createTime);
		
		String name = cost.getName();
		if(name==null || name.trim().isEmpty()){
			throw new ServiceException("�ʷ��������ֲ���Ϊ��");
		}
		if("����".equals(name)){
			cost.setCostType(YEAR_COSTTYPE);
		}else if("����".equals(name)){
			cost.setCostType(MONTH_COSTTYPE);
		}else if("��ʱ�շ�".equals(name)){
			cost.setCostType(HOUR_COSTTYPE);
		}else{
			cost.setCostType(PRICE_COSTTYPE);
		}
		
		costDao.addCost(cost);
		costDao.updateKey();
	}

	//�޸��ʷ���Ϣ
	@Override
	@Transactional
	public void updateCost(Cost cost) throws Exception {
		if(cost==null){
			throw new ServiceException("�ʷ���Ϣ����Ϊ��");
		}
		Cost oldCost = costDao.queryByCostId(cost.getCostId());
		cost.setStatus(oldCost.getStatus());
		cost.setCreateTime(oldCost.getCreateTime());
		
		costDao.updateCost(cost);
		costDao.updateKey();
	}

	//�����ʷ�ID�����ʷ�״̬(ֻ�ܽ�Ĭ�ϵ���ͣ״̬��Ϊ��ͨ״̬)
	@Override
	@Transactional
	public void updateStatus(int costId) throws Exception {
		costDao.updateStatus(costId);
		Cost cost = costDao.queryByCostId(costId);
		cost.setStartTime(new Date());
		costDao.updateCost(cost);
	}

	//�����ʷ�IDɾ���ʷ���Ϣ
	@Override
	@Transactional
	public void deleteCost(int costId) throws Exception {
		costDao.deleteCost(costId);
		costDao.updateKey();
	}

}

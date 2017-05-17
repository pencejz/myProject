package edu.nclg.netctoss.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Cost;
import edu.nclg.netctoss.service.CostService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * �ʷ���Ϣ���Ʋ�
 * @author pjz
 * 
 * @Controller ����Ϊ���Ʋ�
 * @RequestMapping URL��ַӳ��
 * @Resource Ĭ�ϰ�������ע��
 * @ResponseBody ����Ӧ���󣬿��Ա�ע�κζ���
 * @RequestBody ���������Spring��������Э��ת��
 * @PathVariable ���������
 */
@Controller
@RequestMapping("/cost")
public class CostController implements Serializable{

	private static final long serialVersionUID = -8164496746684317178L;

	@Resource
	private CostService costService;
	
	//��ѯ�����ʷ���Ϣ
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Cost>> queryAll(){
		try {
			List<Cost> list = costService.queryAll();
			System.out.println(list);
			return new JsonResult<List<Cost>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Cost>>("��ѯ�����ʷ���Ϣʧ��");
		}
	}

	//�����ʷ�ID��ѯ�ʷ���Ϣ
	@RequestMapping("/queryByCostId/{costId}")
	@ResponseBody
	public JsonResult<Cost> queryByCostId(@PathVariable int costId){
		try {
			Cost cost = costService.queryByCostId(costId);
			return new JsonResult<Cost>(cost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("�����ʷ�ID��ѯ�ʷ���Ϣʧ��");
		}
	}

	//����ʷ���Ϣ
	@RequestMapping("/addCost")
	@ResponseBody
	public JsonResult<Cost> addCost(@RequestBody Cost cost){
		try {
			if(cost == null){
				return new JsonResult<Cost>("�ʷ���Ϣ����Ϊ��");
			}
			costService.addCost(cost);
			return new JsonResult<Cost>(cost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("�ʷ���Ϣ���ʧ��");
		}
	}

	//�޸��ʷ���Ϣ
	@RequestMapping("/updateCost")
	@ResponseBody
	public JsonResult<Cost> updateCost(@RequestBody Cost cost){
		try {
			if(cost == null){
				return new JsonResult<Cost>("�ʷ���Ϣ����Ϊ��");
			}
			System.out.println("updateCost-----"+cost);
			costService.updateCost(cost);
			return new JsonResult<Cost>(JsonResult.SUCCESS,"�ʷ���Ϣ�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("�ʷ���Ϣ�޸�ʧ��");
		}
	}

	//�����ʷ�ID�����ʷ�״̬(costId)
	@RequestMapping("/updateStatus/{costId}")
	@ResponseBody
	public JsonResult<Cost> updateStatus(@PathVariable int costId){
		try {
			costService.updateStatus(costId);
			Cost updateCost = costService.queryByCostId(costId);
			return new JsonResult<Cost>(updateCost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("�ʷ���Ϣ�޸�ʧ��");
		}
	}

	//�����ʷ�IDɾ���ʷ���Ϣ
	@RequestMapping("/deleteCost/{costId}")
	@ResponseBody
	public JsonResult<Cost> deleteCost(@PathVariable int costId){
		try {
			costService.deleteCost(costId);
			return new JsonResult<Cost>(JsonResult.SUCCESS,"�ʷ���Ϣɾ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("�ʷ���Ϣ�޸�ʧ��");
		}
	}
	
	
	
	
	
	
	
	
	
	
}

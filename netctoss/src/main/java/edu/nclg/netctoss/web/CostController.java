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
 * 资费信息控制层
 * @author pjz
 * 
 * @Controller 声明为控制层
 * @RequestMapping URL地址映射
 * @Resource 默认按照名称注入
 * @ResponseBody 绑定响应对象，可以标注任何对象
 * @RequestBody 绑定请求对象，Spring会帮你进行协议转换
 * @PathVariable 绑定请求参数
 */
@Controller
@RequestMapping("/cost")
public class CostController implements Serializable{

	private static final long serialVersionUID = -8164496746684317178L;

	@Resource
	private CostService costService;
	
	//查询所有资费信息
	@RequestMapping("/queryAll")
	@ResponseBody
	public JsonResult<List<Cost>> queryAll(){
		try {
			List<Cost> list = costService.queryAll();
			System.out.println(list);
			return new JsonResult<List<Cost>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Cost>>("查询所有资费信息失败");
		}
	}

	//根据资费ID查询资费信息
	@RequestMapping("/queryByCostId/{costId}")
	@ResponseBody
	public JsonResult<Cost> queryByCostId(@PathVariable int costId){
		try {
			Cost cost = costService.queryByCostId(costId);
			return new JsonResult<Cost>(cost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("根据资费ID查询资费信息失败");
		}
	}

	//添加资费信息
	@RequestMapping("/addCost")
	@ResponseBody
	public JsonResult<Cost> addCost(@RequestBody Cost cost){
		try {
			if(cost == null){
				return new JsonResult<Cost>("资费信息不能为空");
			}
			costService.addCost(cost);
			return new JsonResult<Cost>(cost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("资费信息添加失败");
		}
	}

	//修改资费信息
	@RequestMapping("/updateCost")
	@ResponseBody
	public JsonResult<Cost> updateCost(@RequestBody Cost cost){
		try {
			if(cost == null){
				return new JsonResult<Cost>("资费信息不能为空");
			}
			System.out.println("updateCost-----"+cost);
			costService.updateCost(cost);
			return new JsonResult<Cost>(JsonResult.SUCCESS,"资费信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("资费信息修改失败");
		}
	}

	//根据资费ID更改资费状态(costId)
	@RequestMapping("/updateStatus/{costId}")
	@ResponseBody
	public JsonResult<Cost> updateStatus(@PathVariable int costId){
		try {
			costService.updateStatus(costId);
			Cost updateCost = costService.queryByCostId(costId);
			return new JsonResult<Cost>(updateCost);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("资费信息修改失败");
		}
	}

	//根据资费ID删除资费信息
	@RequestMapping("/deleteCost/{costId}")
	@ResponseBody
	public JsonResult<Cost> deleteCost(@PathVariable int costId){
		try {
			costService.deleteCost(costId);
			return new JsonResult<Cost>(JsonResult.SUCCESS,"资费信息删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Cost>("资费信息修改失败");
		}
	}
	
	
	
	
	
	
	
	
	
	
}

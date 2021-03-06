package edu.nclg.netctoss.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nclg.netctoss.entity.RolePermission;
import edu.nclg.netctoss.service.AdminService;
import edu.nclg.netctoss.service.RolePermissionService;

/**
 * 权限拦截器
 * 访问netctoss各个功能前，检查登录的管理员是否拥有该权限
 */

public class LoginFilter implements Filter {
	
	public String path;
	ApplicationContext ac;

	public void init(FilterConfig arg0) throws ServletException {
		ac = new ClassPathXmlApplicationContext("spring/spring-mybatis.xml","spring/spring-service.xml");
	}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain fchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//获取访问路径
		path = req.getRequestURL().toString();
		System.out.println(path);
		//过滤路径 
		if(path.endsWith("_list.html")){
			redirect(req, res, fchain);
			return;
		}
		fchain.doFilter(req, res);
	}
	
	//访问权限定义
	private void redirect(HttpServletRequest req, HttpServletResponse res, FilterChain fchain) throws IOException, ServletException {
		try {
			String adminName = (String) req.getSession().getAttribute("adminName");	//从session中获得管理员登录名
			if((adminName==null || adminName.trim().isEmpty()) && !path.endsWith("login.html")){
				String url = req.getContextPath() + "/netctoss/error.html";	//未登录
				res.sendRedirect(url);
			}
			AdminService adminService = ac.getBean("adminService", AdminService.class);	//根据管理员登录获得角色ID
			int roleId = adminService.queryByLoginName(adminName).getRoleId();
			//根据角色ID获得权限ID集
			RolePermissionService rolePermissionService = ac.getBean("rolePermissionService", RolePermissionService.class);
			List<RolePermission> rps = rolePermissionService.queryRolePermissionByRoleId(roleId);
			for(RolePermission rp:rps){
				int pid = rp.getPermissionId();
				if(pid==1 && (path.endsWith("role_list.html"))){	//角色管理员
					fchain.doFilter(req, res);
					return;
				}else if(pid==2 && path.endsWith("admin_list.html")){	//人员管理员
					fchain.doFilter(req, res);
					return;
				}else if(pid==3 && path.endsWith("fee_list.html")){	//资费管理员
					fchain.doFilter(req, res);
					return;
				}else if(pid==4 && path.endsWith("account_list.html")){	//账务账户管理员
					fchain.doFilter(req, res);
					return;
				}else if(pid==5 && path.endsWith("service_list.html")){	//业务账户管理员
					fchain.doFilter(req, res);
					return;
				}else if(pid==6 && path.endsWith("bill_list.html")){	//账单管理员
					fchain.doFilter(req, res);
					return;
				}
			}
			//没有权限
			String url = req.getContextPath() + "/netctoss/nopower.html";
			res.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
	}
    
    
    
}
















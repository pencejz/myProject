package edu.nclg.netctoss.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.nclg.netctoss.entity.Admin;
import edu.nclg.netctoss.service.AdminService;
import edu.nclg.netctoss.util.JsonResult;

/**
 * 管理员登录
 * @author pjz
 */
@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController implements Serializable{

	private static final long serialVersionUID = -3214976597125442422L;
	
	@Resource
	private AdminService adminService;
	
	/**
	 * 判断用户登录信息是否正确
	 * @param adminName 
	 * @param adminPwd
	 * @param code	验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/login/{adminName}/{adminPwd}/{code}")
	@ResponseBody
	public JsonResult<Object> testLogin(@PathVariable String adminName, @PathVariable String adminPwd,
						@PathVariable String code,HttpServletRequest request){
		try {
			if(adminName==null || adminName.trim().isEmpty()){
				return new JsonResult<Object>("登录名不能为空");
			}
			if(adminPwd==null || adminPwd.trim().isEmpty()){
				return new JsonResult<Object>("登录密码不能为空");
			}
			if(code==null || code.trim().isEmpty()){
				return new JsonResult<Object>("验证码不能为空");
			}
			
			Admin admin = adminService.queryByLoginName(adminName); //登录名唯一，list中仅有一个元素
			if(admin==null || !admin.getPassword().equals(adminPwd)){
				return new JsonResult<Object>("用户名或密码不正确，请重新输入");
			}
			String imgcode = (String) request.getSession().getAttribute("code"); //从session中获取验证码
			if(code.equals(imgcode)){
				return new JsonResult<Object>("验证码输入错误，请重新输入");
			}
			
			//成功登录之后，将登录信息保存至session中
			HttpSession session = request.getSession();
			session.setAttribute("adminName", adminName);
			session.setAttribute("adminPwd", adminPwd);
//			System.out.println(session.getAttribute("userName"));
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"登录成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("用户名或密码不正确，请重新输入");
		}
	}
	
	
	/**
	 * 验证码生成
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/code", produces="image/png")
	@ResponseBody
	public byte[] code(HttpServletRequest request){
		try {
			byte[] buf;
			//新建一个照片对象（宽，高，像素点的字节数为3（rgb））
			BufferedImage img = new BufferedImage(80,30,BufferedImage.TYPE_3BYTE_BGR);
			//设置图片大小和颜色
			for(int y=0;y<img.getHeight();y++){
				for(int x=0;x<img.getWidth();x++){
					img.setRGB(x, y, 0xEEEEEE);
				}
			}
			//添加1000个麻点
			for(int i=0;i<1000;i++){
				int x = (int)(Math.random()*80);
				int y = (int)(Math.random()*30);
				int rgb = (int)(Math.random()*0xffffff);
				img.setRGB(x, y, rgb);
			}
			//添加图片上的验证码
			Graphics2D g = img.createGraphics();
			int rgb = (int)(Math.random()*0xffffff);
			g.setColor(new Color(rgb));
			Font font = new Font(Font.SANS_SERIF,Font.ITALIC,25);
			g.setFont(font);
			//抗锯齿，平滑绘制
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*5);
			String code = randomCode();
			g.drawString(code, 3+x, 28-y);
			//绘制5条乱线
			for(int i=0;i<5;i++){
				int x1=(int)(Math.random()*80);
				int x2=(int)(Math.random()*80);
				int y1=(int)(Math.random()*30);
				int y2=(int)(Math.random()*30);
				rgb = (int) (Math.random() * 0xffffff);
				g.setColor(new Color(rgb));
				g.drawLine(x1, y1, x2, y2);
			}
			//字节数组流
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//write方法：将照片对象以png格式写到字节数组out中
			ImageIO.write(img, "png", out);
			out.close();
			//将out中的数组复制到buf数组中
			buf = out.toByteArray();
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	private String randomCode() {
		String chs="345678ABCDEFHJKLXYabcdrhknp";
		char[] code = new char[4];
		for(int i=0;i<code.length;i++){
			int index = (int)(Math.random()*chs.length());
			code[i] = chs.charAt(index);
		}
		return new String(code);
	}

	
	
}


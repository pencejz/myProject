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
 * ����Ա��¼
 * @author pjz
 */
@Controller
@RequestMapping("/adminLogin")
public class AdminLoginController implements Serializable{

	private static final long serialVersionUID = -3214976597125442422L;
	
	@Resource
	private AdminService adminService;
	
	/**
	 * �ж��û���¼��Ϣ�Ƿ���ȷ
	 * @param adminName 
	 * @param adminPwd
	 * @param code	��֤��
	 * @param request
	 * @return
	 */
	@RequestMapping("/login/{adminName}/{adminPwd}/{code}")
	@ResponseBody
	public JsonResult<Object> testLogin(@PathVariable String adminName, @PathVariable String adminPwd,
						@PathVariable String code,HttpServletRequest request){
		try {
			if(adminName==null || adminName.trim().isEmpty()){
				return new JsonResult<Object>("��¼������Ϊ��");
			}
			if(adminPwd==null || adminPwd.trim().isEmpty()){
				return new JsonResult<Object>("��¼���벻��Ϊ��");
			}
			if(code==null || code.trim().isEmpty()){
				return new JsonResult<Object>("��֤�벻��Ϊ��");
			}
			
			Admin admin = adminService.queryByLoginName(adminName); //��¼��Ψһ��list�н���һ��Ԫ��
			if(admin==null || !admin.getPassword().equals(adminPwd)){
				return new JsonResult<Object>("�û��������벻��ȷ������������");
			}
			String imgcode = (String) request.getSession().getAttribute("code"); //��session�л�ȡ��֤��
			if(code.equals(imgcode)){
				return new JsonResult<Object>("��֤�������������������");
			}
			
			//�ɹ���¼֮�󣬽���¼��Ϣ������session��
			HttpSession session = request.getSession();
			session.setAttribute("adminName", adminName);
			session.setAttribute("adminPwd", adminPwd);
//			System.out.println(session.getAttribute("userName"));
			
			return new JsonResult<Object>(JsonResult.SUCCESS,"��¼�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>("�û��������벻��ȷ������������");
		}
	}
	
	
	/**
	 * ��֤������
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/code", produces="image/png")
	@ResponseBody
	public byte[] code(HttpServletRequest request){
		try {
			byte[] buf;
			//�½�һ����Ƭ���󣨿��ߣ����ص���ֽ���Ϊ3��rgb����
			BufferedImage img = new BufferedImage(80,30,BufferedImage.TYPE_3BYTE_BGR);
			//����ͼƬ��С����ɫ
			for(int y=0;y<img.getHeight();y++){
				for(int x=0;x<img.getWidth();x++){
					img.setRGB(x, y, 0xEEEEEE);
				}
			}
			//���1000�����
			for(int i=0;i<1000;i++){
				int x = (int)(Math.random()*80);
				int y = (int)(Math.random()*30);
				int rgb = (int)(Math.random()*0xffffff);
				img.setRGB(x, y, rgb);
			}
			//���ͼƬ�ϵ���֤��
			Graphics2D g = img.createGraphics();
			int rgb = (int)(Math.random()*0xffffff);
			g.setColor(new Color(rgb));
			Font font = new Font(Font.SANS_SERIF,Font.ITALIC,25);
			g.setFont(font);
			//����ݣ�ƽ������
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*5);
			String code = randomCode();
			g.drawString(code, 3+x, 28-y);
			//����5������
			for(int i=0;i<5;i++){
				int x1=(int)(Math.random()*80);
				int x2=(int)(Math.random()*80);
				int y1=(int)(Math.random()*30);
				int y2=(int)(Math.random()*30);
				rgb = (int) (Math.random() * 0xffffff);
				g.setColor(new Color(rgb));
				g.drawLine(x1, y1, x2, y2);
			}
			//�ֽ�������
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			//write����������Ƭ������png��ʽд���ֽ�����out��
			ImageIO.write(img, "png", out);
			out.close();
			//��out�е����鸴�Ƶ�buf������
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


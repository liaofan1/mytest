package cn.itheima.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itheima.ssm.exception.MyException;
import cn.itheima.ssm.po.Item;
import cn.itheima.ssm.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//@RequestMapping("/queryItem.do")
	@RequestMapping(value={"queryItem","queryItem1"},method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView queryItem(String id) {
		
		
		ModelAndView mav = new ModelAndView();
		List<Item> itemList = itemService.queryItemList();
		mav.addObject("itemList", itemList);
		mav.addObject("id", id);
		// 3.设置响应的模型数据
		mav.setViewName("item/itemList");
		return mav;
		
	}
	
	// 根据商品Id查询商品
		//http://127.0.0.1:8080/ssm/queryItemById.do?id=1
		// request参数：用于接收请求的商品Id参数。springmvc在执行这个方法的时候，会传递request对象
	@RequestMapping("/queryItemById.do")
	public String  queryItemById(Model model, Integer id) throws MyException{
		
	
		Item item = itemService.queryItemById(id);
		/*ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.setViewName("item/itemEdit");*/
		
		// 异常测试==============
		//int i = 1/0;
		if (item==null) {
			throw new MyException("商品不存在");
		}
		
		// 异常测试==============
		
		// 3.使用Model设置响应的模型数据
				// addAttribute方法：设置响应的模型数据
				// 参数一：模型的名称
				// 参数二：模型数据
		model.addAttribute("item", item);
		return "item/itemEdit";
	}
	
	// 修改商品，保存数据库
		// http://127.0.0.1:8080/ssm/updateItem.do
		// 使用商品pojo接收请求的商品参数数据，pojo属性的名称与请求参数的名称一致
	// 增加形参pictureFile，接收请求的文件上传参数，形参的名称与file组件的name属性一致
	@RequestMapping("/updateItem.do")
	public String updateItem(Item item,MultipartFile pictureFile){
		
		// 文件上传实现逻辑=======================
		// 1.判断文件是否为空
		if (pictureFile!=null) {
			// 2.重命名文件名称
						// 2.1获取原始文件名称
			String oriname = pictureFile.getOriginalFilename();
			String extname = oriname.substring(oriname.lastIndexOf("."));
			// 2.2产生新的文件名称
			String newName = System.currentTimeMillis()+"";
			newName += extname ;
			
			//3.上传文件
			File file = new File("E:\\pic",newName);
			try {
				pictureFile.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 4.设置item的pic属性
			item.setPic(newName);
			
		}
		
		
		
		
		// 文件上传实现逻辑=======================
		try {
			itemService.updateItem(item);
		} catch (Exception e) {

			e.printStackTrace();
			return "common/failure";
		}
		// 如果修改成功，返回成功提示页面
		return "common/success";
		
	}
	
	@RequestMapping("/returnVoid.do")
	public void controllerReturnVoid(HttpServletRequest request,HttpServletResponse response){
		
		try{
			/*request.getRequestDispatcher("queryItem.do")
			.forward(request, response);*/
			/*response.sendRedirect("queryItem.do");*/
			//response.sendRedirect("http://www.baidu.com");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write("json字符串");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@RequestMapping("/returnString.do")
	public String controllerReturnString(){
		// 1.forward转发
				// 格式：forward关键字+":"+目标url
		//return "forward:queryItem.do";
		return "redirect:queryItem.do";
		
	}
	
	@RequestMapping("/testJson.do")
	@ResponseBody
	public Item testJson(@RequestBody Item item){
		return item;	
	}
	
	// restful讲解专用
		// http://127.0.0.1:8080/ssm/rest/1 
		// {id}:路径变量（模版参数）
		// @PathVariable(value="id")注解：把路径变量值，绑定到形参上
	@RequestMapping("/rest/{id}")
	@ResponseBody
	public Item testRestful(@PathVariable(value="id") Integer id){
		
		// 1.查询商品对象
		Item item = itemService.queryItemById(id);
		return item;
		
	}
	
	// 拦截器讲解专用
	@RequestMapping("/interceptor.do")
	public String testInterceptor(Model model){
		System.out.println("ItemController-testInterceptor方法执行中......");
		model.addAttribute("message1", "我是处理器方法testInterceptor......");
		return "inter/interceptor";
		
	}

}

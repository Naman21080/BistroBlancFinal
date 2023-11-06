package com.bistroblanc.BistroBlanc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BistroController {

	@Autowired
	ItemsInterRepository it;
	
	@Autowired
	UsersInterRepository ut;
	
	@Autowired
	CartInterRepository ct;
	
	@RequestMapping("home")
	public String home() 
	{
		return "Home.jsp";
	}
	
	@RequestMapping("about")
	public String about() 
	{
		return "About.jsp";
	}
	
	@RequestMapping("login")
	public String login(String email,String password) 
	{   users ud=ut.findByEmail(email);
	    if(ud !=null && ud.getPassword().equals(password)){
	    	
	    	return "home";
	    }
	    else {
	    	return "Login.jsp";
	    }
	}
	
	@RequestMapping("signup")
	public String signup() 
	{
		return "Signup.jsp";
	}
	
	@RequestMapping("additems")
	public String additems(){
		return "AddItems.jsp";
	}
	
	@RequestMapping("addFoodItems")
	public String addFoodItems(String category,String item_name, String item_description, int votes, int price,
			String img_url) {
		
		items im=new items();
		
		im.setCategory(category);
		im.setItem_name(item_name);
		im.setItem_description(item_description);
		im.setVotes(votes);
		im.setPrice(price);
		im.setImg_url(img_url);
		
		it.save(im);
		
		return "redirect:/allitems";
	}
	
	@RequestMapping("addUserDetails")
	public String addUserDetails(String name, String mob_no, String email, String password, String country, String city,
			String pincode) {
		
		users ur=new users();
		ur.setName(name);
		ur.setMob_no(mob_no);
		ur.setEmail(email);
		ur.setPassword(password);
		ur.setCountry(country);
		ur.setCity(city);
		ur.setPincode(pincode);
		
		ut.save(ur);
		
		return "redirect:/home";
		
	}
	
	@RequestMapping("allitems")
	public ModelAndView allitems(ModelMap mp) 
	{
		List<items> li =(List<items>) it.findAllByCategory("Main course");
		mp.put("object",li);
		
		List<items> lf =(List<items>) it.findAllByCategory("Fast Food");
		mp.put("object2",lf);
		
		List<items> lt =(List<items>) it.findAllByCategory("Dessert Items");
		mp.put("object3",lt);
		
		ModelAndView mv=new ModelAndView();
		mv.addAllObjects(mp);
		mv.setViewName("Allitems.jsp");
		return mv;
	}
	
	
	@RequestMapping("singleitem")
	public ModelAndView singleitem(ModelMap mo,int item_id) 
	{
		items i1= it.findById(item_id).get();
		mo.put("object1",i1);
		ModelAndView mv =new ModelAndView();
		mv.addObject(mo);
		mv.setViewName("SingleItem.jsp");
		return mv;
	}
	
	@RequestMapping("cartItems")
	public ModelAndView cartItems(ModelMap mt) {
		
		mt.put("object4",ct.findAll());
		ModelAndView mv=new ModelAndView();
		mv.addAllObjects(mt);
		mv.setViewName("CartItems.jsp");
		return mv;
	}
	
	//public String check(){
		//yaha ye karna hh ki agar bynow p click krte hi chck kre login h to cart items p aye or nhi hh to login krne ka option khule
	//}
	
	@RequestMapping("checkout")
	public String checkout() {
		return "Checkout.jsp";
	}
	
}

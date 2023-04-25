package com.example.app.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.api.output.NewOutput;
import com.example.app.dto.NewDTO;
import com.example.app.service.NewsService;

//@Controller // return View HTML
@RestController // = @Controller + @RestponseBody
@RequestMapping("/api") //Endpoint gốc là /api
public class NewAPI {
	
	// Inject Service vào để gọi được
	//thong thuong inject qua constructor
	@Autowired
	private NewsService newsService;

	/*
	 * thuc hien get data
	 * neu param page, limit != null => thuc hien get data theo kieu phan trang
	 * nguoc lai => thuc hien get tat ca data
	 */
	@GetMapping("/new")//@RequestMapping(value = "/new", method = RequestMethod.GET)
	//@ResponseBody
	public NewOutput showNews(@RequestParam(value = "page", required = false) Integer page, //default required = true
								@RequestParam(value = "limit", required = false) Integer limit) {
		NewOutput result = new NewOutput();
		if(page != null && limit != null) { //required = false => cho phep kiem tra param
			result.setPage(page);
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setListResult(newsService.findAll(pageable));
			result.setTotalPage((int)Math.ceil((double) (newsService.totalPage()) / limit));
		}
		else {
			result.setListResult(newsService.findAll());
		}
		return result;
	}
	
	/*
	 * Search
	 */
	@GetMapping("/new/search")
	public NewOutput getSearch(@RequestParam(value = "keyword", required = false) String keyword,
								@RequestParam(value = "page", required = false) Integer page,
								@RequestParam(value = "limit", required = false) Integer limit) {
		NewOutput result = new NewOutput();
		//if(keyword != null || keyword != "") {
			if(page == null && limit == null) { //required = false => cho phep kiem tra param
				page = 1;
				limit = 5;
			}
			result.setPage(page);
			Pageable pageable = PageRequest.of(page - 1, limit);
			result.setListResult(newsService.findAll(keyword, pageable));
			result.setTotalPage((int)Math.ceil((double) (newsService.totalPage()) / limit));
		//}
		return result;
	}
	
	/*
	 * Them moi
	 */
	@PostMapping("/new")
	@RolesAllowed("ROLE_EDITOR")
	public NewDTO createNew(@RequestBody @Valid NewDTO model) {
		return newsService.save(model);
	}
	
	/*
	 * chinh sua
	 */
	@PutMapping("/new/{id}")
	@RolesAllowed("ROLE_EDITOR")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") Long id) {
		model.setId(id);
		return newsService.save(model);
	}
	
	/*
	 * xoa 1 record theo id
	 */
	@DeleteMapping("/new/{id}")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
	public void deleteNew(@PathVariable("id") long id) {
		
		newsService.delete(id);
	}
	
	/*
	 * xoa nhieu record
	 */
	@DeleteMapping("/new")
	@RolesAllowed({"ROLE_ADMIN", "ROLE_EDITOR"})
	public void deleteListNews(@RequestBody Long[] ids) {
		newsService.delete(ids);
	}
	
	/*
	@DeleteMapping("/deleteproduct")
	public ResponseEntity<?> deleteProduct(@Valid @RequestBody Map<String,Object> userMap){

	    List<String> idList=(List<String>) userMap.get("id_list");

	    List<Product> productList=(List<Product>) productRepository.findAllById(idList);
	    productRepository.deleteAll(productList);
	    return ResponseEntity.status(HttpStatus.OK).body("Deleted item : "+productList);

	}
	*/
}

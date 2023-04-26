# spring-boot-news-api
# database: mysql

API test

- đăng ký - url: http://localhost:8080/api/register
Method: POST
{
    "username":"abc",
    "password": "123456"
}
mặc định khi đăng ký với quyền là USER

- đăng nhập - url : http://localhost:8080/api/login
Method: 
{
    "username":"abc",
    "password": "123456"
}
username admin:
{
    "username":"vu",
    "password": "123456"
}

- tạo "danh mục" - http://localhost:8080/api/category
Method: POST
Header : 
  Key : Authorization 
  Value : Example accesstoken
JSON:
{
    "name":"Am nhac",
    "code":"am-nhac"
}

- tạo "bản tin" - url : http://localhost:8080/api/new
Method : POST
Header : 
  Key : Authorization 
  Value : Example accesstoken
JSON :
{
  "title":"text 222226666",
	"content":"text text text 6",
	"shortDescription":"text 666666",
	"categoryCode":"am-nhac",
	"thumbnail":""
}

- cập nhật "bản tin" - url: http://localhost:8080/api/new
Method: PUT
Header : 
  Key : Authorization 
  Value : Example accesstoken
JSON :
{
  "title":"text 22222688886",
	"content":"text text text 6",
	"shortDescription":"text 666666",
	"categoryCode":"am-nhac",
	"thumbnail":""
}

- xóa "bản tin" - url: http://localhost:8080/api/new/{id}
Method: DELETE
Header : 
  Key : Authorization 
  Value : Example accesstoken

- lấy lại token mới khi hết hạn - url: http://localhost:8080/api/token/refresh
Method: GET
Header : 
  Key : Authorization 
  Value : Example refreshtoken
  
=> nhận được accessToken mới

- load dữ liệu bản tin - url: http://localhost:8080/api/new?page=1&limit=2
Method: GET
  

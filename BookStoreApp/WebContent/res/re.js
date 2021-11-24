/**
 * 
 */

function register(url){
	//alert("nimabide");
	var xhr = new XMLHttpRequest();
	var data = '';
	data +="email=" + document.getElementById("email").value;
	data += "&" + "firstname=" + document.getElementById("firstname").value;
	data += "&" + "lastname=" + document.getElementById("lastname").value;
	data += "&" + "password=" + document.getElementById("password").value;
	data += "&" + "address=" + document.getElementById("address").value;
	data += "&" + "city=" + document.getElementById("city").value;
	data += "&" + "province=" + document.getElementById("province").value;
	data += "&" + "zip=" + document.getElementById("zip").value;
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		handler(xhr);
	};
	xhr.send(data);
}

function handler(r){
	if((r.readyState == 4) && (r.status == 200)){
		var rt = JSON.parse(r.responseText);
		if(rt.errMessage == null){
			window.location.href='login?message=ok';
			
			
		}else{
			document.getElementById("error").innerHTML=rt.errMessage;
			document.getElementById("error").style.color="red";
		}
	}
}

function changeimg(url){
	
	var xhr = new XMLHttpRequest();
	
	
	
	var data = '';
	var inpu = document.getElementById("fp").value;
	data += "filePhoto=" + inpu;
	
	
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		cihand(xhr);
	};
	xhr.send(data);
}

function cihand(r){
	
	if((r.readyState == 4) && (r.status == 200)){
		var rt = JSON.parse(r.responseText);
		if(rt.errMessage == null){
			var im = document.getElementById("userimg");
			im.src = "userIMG/" + rt.okMessage;
			location.reload();
		}else{
			
		}
	}
}


function validate(url){

	var xhr = new XMLHttpRequest();
	var data = '';
	data +="email=" + document.getElementById("email").value;
	
	data += "&" + "password=" + document.getElementById("password").value;
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		validateResult(xhr);
	};
	xhr.send(data);
}

function validateResult(r){
	if((r.readyState == 4) && (r.status == 200)){
		var rt = JSON.parse(r.responseText);
		if(rt.errMessage == null){
			alert("login successful");
			window.location.href="main";
			
			
		}else{
				document.getElementById("okRegister").style.display = "none";
				document.getElementById("errorMessage").innerHTML="Email or Password is incorrect, please check again";
				document.getElementById("errorMessage").style.color="red";
			
		}
	}
}



function spread(para){
	alert("run");
	var submenu = document.getElementById(para);
	var dis = submenu.style.display;
	if(dis == "block"){
		submenu.style.display = "none";
	}else{
		submenu.style.display = "block";
	}
}




function searchValidate(){
	var ok = true;
	var title = document.getElementById("title").value;
	if(title == null || title == ""){
		return false;
	}else{
	return ok;
}
}

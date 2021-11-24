/**
 * 
 */

function addToCart(id){
	
	var xhr = new XMLHttpRequest();
	var data = '';
	data +="bid=" + id;
	
	xhr.open("POST","addToCart",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		message(xhr);
	};
	xhr.send(data);
}

function removeFromCart(id){
	var xhr = new XMLHttpRequest();
	var data = '';
	data +="bid=" + id;
	
	xhr.open("POST","removeFromCart",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		message(xhr);
	};
	xhr.send(data);
}

function minusFromCart(id){
	var xhr = new XMLHttpRequest();
	var data = '';
	data +="bid=" + id;
	
	xhr.open("POST","minusFromCart",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		message(xhr);
	};
	xhr.send(data);
}

function message(r){
	if((r.readyState == 4) && (r.status == 200)){
		var rt = JSON.parse(r.responseText);
		if(rt.errMessage == null){
			alert(rt.okMessage);
			window.location.reload();
			
			
		}else{
			alert(rt.errMessage);
		}
	}
}
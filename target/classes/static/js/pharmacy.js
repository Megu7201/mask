
function modify(event){
	var name = event.getAttribute("title");
	var id = event.getAttribute("alt");
	var note = event.getAttribute("src");
	Swal.fire({
		 title:"修改備註",
		 text:name,
		 input:"text",
		 inputValue:note,
		 showCancelButton: true,
		 preConfirm:(note)=>{
			 var data = {"pharmacyId":id,"note":note};
			 return fetch(`pharmacy/${id}/note`,{
				 method:"PUT",
				 body:JSON.stringify(data),
				 headers: {
					  'Content-Type': 'application/json',
				      'charset':'utf-8'
				    },
			 }).then((result) => {
				 if(result.ok)
					 {
						 Swal.fire({
							 title:'備註已更改',
							 text:name,
							 icon:'success',
							 timer:2000,
							 onClose: () => {
								    location.reload()
								  }
						 })
					 }
				 else{
					 Swal.fire(
						 'Failed',
						 '錯誤',
						 'error'
					 )
				 }
				 
			 }); 
		 }
		});
}
function del(event){
	var name = event.getAttribute("title");
	var id = event.getAttribute("alt");
	Swal.fire({
		 title:"刪除備註",
		 text:name,
		 showCancelButton: true,
		 preConfirm:()=>{
			 var data = {"pharmacyId":id};
			 return fetch(`pharmacy/${id}/note`,{
				 method:"DELETE",
				 body:JSON.stringify(data),
				 headers: {
					  'Content-Type': 'application/json',
				      'charset':'utf-8'
			     },
			 }).then((result) => {
				 if(result.ok){
					 Swal.fire({
						 title:'備註已刪除',
						 text:name,
						 icon:'success',
						 timer:2000,
						 onClose: () => {
							 location.reload()
						 }
					 })
				 }
				 else{
					 Swal.fire(
						 'Failed',
						 '錯誤',
						 'error'
					 )
				 }
				 
			 }); 
		 }
		});
}
function add(event){
	var name = event.getAttribute("title");
	var id = event.getAttribute("alt");
	
	Swal.fire({
		 title:"新增備註",
		 text:name,
		 input:"text",
		 showCancelButton: true,
		 preConfirm:(note)=>{
			 var data = {"pharmacyId":id,"note":note};
			 return fetch(`pharmacy/${id}/note`,{
				 method:"POST",
				 body:JSON.stringify(data),
				 headers: {
					  'Content-Type': 'application/json',
				      'charset':'utf-8'
				    },
			 }).then((result) => {
				 if(result.ok)
					 {
						 Swal.fire({
							 title:'備註已新增',
							 text:name,
							 icon:'success',
							 timer:2000,
							 onClose: () => {
								    location.reload()
								  }
						 })
					 }
				 else{
					 Swal.fire(
						 'Failed',
						 '錯誤',
						 'error'
					 )
				 }
				 
			 }); 
		 }
	});
}

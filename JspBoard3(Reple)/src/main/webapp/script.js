function writeSave(){
	
	if(document.writeform.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.passwd.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeform.passwd.focus();
	  return false;
	}
 }    

function comment(){
	//alert();
	if(document.add_form.writer_id.value==""){
	  alert("로그인하셔야합니다.");
	  return false;
	}
	if(document.add_form.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.add_form.content.focus();
	  return false;
	}
	document.add_form.submit();

 } 
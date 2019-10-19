function createFormData(data){
	let formData = new FormData();
	if(typeof data == 'object'){
		  for(let i in data){
		    formData.append(i,data[i])
		  }
		  return formData
		}else if(typeof data == 'string'){
			formData.append(`${data}`,data);
		}
  return formData
}
export {createFormData}
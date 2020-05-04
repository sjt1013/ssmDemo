/*
 *程序功能：远程数据下拉框绑定，同时默认选中第一个
 *$source,待绑定jQuery对象
 *sUrl，数据加载地址
 *oField，绑定模型，默认{k:"id",v:"text"}
 *ckId，待绑定ID，-1代表暂不绑定
 */

function bindCombox($source,sUrl,oField,ckId){
	$source.combobox({
		url:sUrl,
		valueField:oField.k,
		textField:oField.v,
		onLoadSuccess:function(){
			var array=$(this).combobox('getData');
			if(array.length>0){
				if(ckId ==-1){
					$(this).combobox('select',array[0][oField.k]);
				}else{
					$(this).combobox('select',ckId);
				}
				
			}
		}
	});
	
}

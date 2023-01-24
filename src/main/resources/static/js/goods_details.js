function addNextGoodsSection() {
	allDivGoods = $("[id^='divGood']");
	dicGoodsCount = allDivGoods.length;
	
	htmlGoodSection = `
		<div  class="row " id="divGood0"> 
		  	<div class="col-sm-2 ">	    
		  		<textarea class="form-control " rows="3" cols="10" placeholder="Packing & Marking:" name="goodMarkings" ></textarea>   	    
			</div>	
			<div class="col-sm-5 ">	        
			     <textarea class="form-control " rows="3" cols="25" placeholder="Number & Type of goods name:" name="goodDescriptions"></textarea>
			</div>
			<div class="col-sm-2 ">
				<textarea class="form-control" rows="3" cols="10" placeholder="Origin criteria:" name="goodCriterias"></textarea>	    
			    
			</div>
			<div class="col-sm-2">	  
				<textarea class="form-control " rows="3" cols="10" placeholder="Gross weight or other measurement units:" name="goodQuantities"></textarea>  
			    
			</div>  	 
		  </div>
	`;	
	
	$("#divGoodDetails").append(htmlGoodSection);
	
	previousDivGoodSection = allDivGoods.last();
	previousDivGoodId = previousDivGoodSection.attr("id");
		
}


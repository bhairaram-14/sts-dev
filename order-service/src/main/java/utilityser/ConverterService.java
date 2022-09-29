package utilityser;

import com.sts.api.common.OrderDto;
import com.sts.api.entity.Order;

public class ConverterService {

  public static OrderDto entityToDtoOrder(Order order)
  {
	  OrderDto dto=new OrderDto();
	  
	  dto.setName(order.getName());
	  dto.setOrderId(order.getOrderId());
	  dto.setOrderStatus(order.getOrderStatus());
	  dto.setPrice(order.getPrice());
	  dto.setQuantity(order.getQuantity());
	  
	 return dto; 
  }
  
  public static Order DtoToEntity(OrderDto order)
  {
	  Order  entity=new Order();
	  
	  entity.setName(order.getName());
	  entity.setOrderId(order.getOrderId());
	  entity.setOrderStatus(order.getOrderStatus());
	  entity.setPrice(order.getPrice());
	  entity.setQuantity(order.getQuantity());
	  
	 return entity; 
  }
  
}

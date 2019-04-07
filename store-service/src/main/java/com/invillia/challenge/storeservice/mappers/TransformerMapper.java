package com.invillia.challenge.storeservice.mappers;

import com.invillia.challenge.storeservice.entities.Order;
import com.invillia.challenge.storeservice.entities.OrderItem;
import com.invillia.challenge.storeservice.entities.Payment;
import com.invillia.challenge.storeservice.entities.Store;
import com.invillia.challenge.storeservice.models.OrderDTO;
import com.invillia.challenge.storeservice.models.OrderItemDTO;
import com.invillia.challenge.storeservice.models.PaymentDTO;
import com.invillia.challenge.storeservice.models.StoreDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@Component
@Mapper(componentModel = "spring")
public interface TransformerMapper {


    Store storeDTOtoStore(StoreDTO dto);

    StoreDTO storeDTOtoStore(Store entity);

    List<Store> storeDTOtoStore(List<StoreDTO> dtos);

    List<StoreDTO> storetoStoreDTO(List<Store> entity);

    OrderDTO orderDTOToOrder(Order entity);

    Order orderDTOtoOrder(OrderDTO dto);

    OrderItemDTO orderItemToOrderItemDTO(OrderItem entity);

    OrderItem orderItemDTOtoOrderItem(OrderItemDTO dto);

    PaymentDTO paymentToPaymentDTO(Payment entity);

    Payment paymentDTOtoPayment(PaymentDTO dto);
}

package com.ats.feastwebapi.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.feastwebapi.model.ErrorMessage;
import com.ats.feastwebapi.model.Order;
import com.ats.feastwebapi.model.OrderDetails;
import com.ats.feastwebapi.model.OrderDetailsList;
import com.ats.feastwebapi.model.OrderList;
import com.ats.feastwebapi.repository.OrderDetailRepository;
import com.ats.feastwebapi.repository.OrderListRepository;
import com.ats.feastwebapi.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	OrderListRepository orderListRepository;

	// -----------------Order --------------------

	@RequestMapping(value = { "/saveOrder" }, method = RequestMethod.POST)
	public @ResponseBody Order saveOrder(@RequestBody Order order) {

		Order orderRes = new Order();
		try {

			List<OrderDetails> orderDetailList = new ArrayList<>();

			orderRes = orderRepository.saveAndFlush(order);

			System.out.println("BEAN : -------------------" + order.getOrderDetailList());
			for (int i = 0; i < order.getOrderDetailList().size(); i++) {

				orderRes.getOrderDetailList().get(i).setOrderId(orderRes.getOrderId());

				/*OrderDetails orderDetailRes = orderDetailRepository.saveAndFlush(orderRes.getOrderDetailList().get(i));*/
				/*orderDetailList.add(orderDetailRes);*/

			}
			orderDetailList = orderDetailRepository.saveAll(order.getOrderDetailList());
			
			orderRes.setOrderDetailList(orderDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return orderRes;

	}

	@RequestMapping(value = { "/getNoOfKot" }, method = RequestMethod.POST)
	public @ResponseBody List<OrderList> getNoOfKot(@RequestParam("tableNo") int tableNo) {

		List<OrderList> order = null;
		try {
			order = orderListRepository.findByTableNoAndBillStatus(tableNo);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return order;

	}

	
	
	@RequestMapping(value = { "/getOrdeByOrderId" }, method = RequestMethod.POST)
	public @ResponseBody Order getOrdeByOrderId(@RequestParam("orderId") int orderId) {

		Order order = null;
		try {
			order = orderRepository.findByOrderId(orderId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return order;

	}

	@RequestMapping(value = { "/getAllOredersByIsUsed" }, method = RequestMethod.GET)
	public @ResponseBody List<Order> getAllOredersByIsUsed() {

		List<Order> orderList = new ArrayList<Order>();

		try {

			orderList = orderRepository.findAllByDelStatus(1);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return orderList;

	}

	@RequestMapping(value = { "/deleteOrder" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteOrder(@RequestParam("orderId") int orderId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = orderRepository.deleteOrder(orderId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage(" Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	@RequestMapping(value = { "/getOrderDetailByOrderDetailId" }, method = RequestMethod.POST)
	public @ResponseBody OrderDetails getOrderDetailByOrderDetailId(
			@RequestParam("orderDetailsId") int orderDetailsId) {

		OrderDetails order = null;
		try {
			order = orderDetailRepository.findByOrderDetailsId(orderDetailsId);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return order;

	}

	@RequestMapping(value = { "/getAllOrderDetailsByIsUsed" }, method = RequestMethod.GET)
	public @ResponseBody List<OrderDetails> getAllOrderDetailsByIsUsed() {

		List<OrderDetails> orderList = new ArrayList<OrderDetails>();

		try {

			orderList = orderDetailRepository.findAll();

		} catch (Exception e) {

			e.printStackTrace();

		}
		return orderList;

	}

	@RequestMapping(value = { "/deleteOrderDetail" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage deleteOrderDetail(@RequestParam("orderDetailsId") int orderDetailsId) {

		ErrorMessage errorMessage = new ErrorMessage();

		try {
			int delete = orderDetailRepository.deleteOrderDetail(orderDetailsId);

			if (delete == 1) {
				errorMessage.setError(false);
				errorMessage.setMessage(" Deleted Successfully");
			} else {
				errorMessage.setError(true);
				errorMessage.setMessage("Deletion Failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			errorMessage.setError(true);
			errorMessage.setMessage("Deletion Failed :EXC");

		}
		return errorMessage;
	}

	/*Sachin 2019-08-11 Not used*/
	@RequestMapping(value = { "/saveOrderWebApp" }, method = RequestMethod.POST)
	public @ResponseBody Order saveOrderWebApp(@RequestBody List<OrderDetailsList> orderDetList) {

		Order orderRes = new Order();
		try {
			
			orderRes.setBillStatus(1);
			String curDate;

				SimpleDateFormat hmsDf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Calendar cal = Calendar.getInstance();

			String curDtTime= hmsDf.format(cal.getTime());

			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			curDate =df.format(new Date());
			orderRes.setDelStatus(1);
			orderRes.setOrderDate(curDate);
			orderRes.setOrderDateTime(curDtTime);
			
			orderRes.setOrderId(0);
			
			orderRes.setTableNo(1);
			orderRes.setUserId(1);
			
			List<OrderDetails> orderDetailList = new ArrayList<>();

			orderRes = orderRepository.saveAndFlush(orderRes);

			for (int i = 0; i < orderDetList.size(); i++) {

				orderRes.getOrderDetailList().get(i).setOrderId(orderRes.getOrderId());
				
				OrderDetails od=new OrderDetails();
				
				od.setIsMixer(0);
				od.setItemId(orderDetList.get(i).getItemId());
				od.setOrderDetailsId(0);
				od.setOrderId(orderRes.getOrderId());
				od.setQuantity(orderDetList.get(i).getQuantity());
				od.setRate(orderDetList.get(i).getRate());
				od.setRemark(orderDetList.get(i).getRemark());
				od.setStatus(1);
				orderDetailList.add(od);

			}
			orderDetailList = orderDetailRepository.saveAll(orderDetailList);
			
			orderRes.setOrderDetailList(orderDetailList);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return orderRes;

	}
}

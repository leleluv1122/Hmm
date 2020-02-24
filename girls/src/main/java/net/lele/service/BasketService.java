package net.lele.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import net.lele.domain.Basket;
import net.lele.repository.BasketRepository;

@Service
public class BasketService {
	@Autowired
	BasketRepository basketRepository;

	public List<Basket> findAll() {
		return basketRepository.findAll();
	}
	/*
	 * public List<Object[]> findBasketOfCount() { return
	 * basketRepository.findBasketOfCount(); }
	 */

	public boolean hasErrors(Basket basket, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return true;
		if(basket.getUser() == null) {
			bindingResult.rejectValue("user", null, "로그인이 필요한 서비스입니다.");
			return true;
		}
		return false;
	}

	public void save(Basket basket) {
		Basket ba = new Basket();
		ba.setCount(basket.getCount());
		ba.setProduct(basket.getProduct());
		ba.setUser(basket.getUser());
		ba.setColor(basket.getColor());
		/* ba.setOption(basket.getOption()); */
		basketRepository.save(ba);
	}

	public void delete(int id) {
		basketRepository.deleteById(id);
	}

}
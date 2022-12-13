package com.AchillBar.base.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.AchillBar.base.model.memberModel;
import com.AchillBar.base.model.dao.memberDao;

@Service
public class memberService {

	@Autowired
	private memberDao mDao;

	public memberModel insert(memberModel member) {
		return mDao.save(member);
	}

	public memberModel findMemberById(Integer id) {
		Optional<memberModel> optional = mDao.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public List<memberModel> findAllMember() {
		return mDao.findAll();
	}

	public void deleteById(Integer id) {
		mDao.deleteById(id);
	}

	public memberModel findByEmailAndPassword(String email, String password) {
		memberModel mb = null;
		mb = mDao.findByEmailAndPassword(email, password);
		return mb;
	}

	public memberModel findByEmail(String email) {
		return mDao.findByEmail(email);
	}

	public Page<memberModel> findAllCustomer(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 5, Sort.Direction.ASC, "m_id");
		return mDao.findAllCustomer(pgb);
	}
}

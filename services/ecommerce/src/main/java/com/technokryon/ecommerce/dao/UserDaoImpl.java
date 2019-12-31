package com.technokryon.ecommerce.dao;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.technokryon.ecommerce.model.TKECMUSER;
import com.technokryon.ecommerce.model.TKECTUSERAUDIT;
import com.technokryon.ecommerce.model.TKECTUSERSESSION;
import com.technokryon.ecommerce.pojo.User;
import com.technokryon.ecommerce.pojo.UserSession;

@Repository("UserDao")
@Transactional
@Component

public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory O_SessionFactory;

	@Autowired
	private ModelMapper O_ModelMapper;

	@Override
	public User isUserEmailAvailable(String mail) {

		String getUserByEmail = "FROM TKECMUSER WHERE uMail =:email";

		Query getUserByEmailQry = O_SessionFactory.getCurrentSession().createQuery(getUserByEmail);
		getUserByEmailQry.setParameter("email", mail);

		TKECMUSER O_TKECMUSER = (TKECMUSER) getUserByEmailQry.uniqueResult();

		if (O_TKECMUSER == null) {

			return null;

		}

		User O_User = O_ModelMapper.map(O_TKECMUSER, User.class);

//		O_PJ_TKECMUSER.setTkecmuPassword(O_TKECMUSER.getTkecmuPassword());
//		O_PJ_TKECMUSER.setTkecmuId(O_TKECMUSER.getTkecmuId());
//		O_PJ_TKECMUSER.setTkecmuStatus(O_TKECMUSER.getTkecmuStatus());
//		O_PJ_TKECMUSER.setTkecmuOtpStatus(O_TKECMUSER.getTkecmuOtpStatus());

		return O_User;

	}

	@Override
	public String createNewUserByEmail(User RO_User) {
		Session O_Session = O_SessionFactory.openSession();
		Transaction O_Transaction = O_Session.beginTransaction();

		String getUserId = "FROM TKECMUSER ORDER BY uId DESC";

		Query userIdQuery = O_SessionFactory.getCurrentSession().createQuery(getUserId);
		userIdQuery.setMaxResults(1);
		TKECMUSER O_TKECM_USER1 = (TKECMUSER) userIdQuery.uniqueResult();

		TKECMUSER O_TKECM_USER = new TKECMUSER();

		if (O_TKECM_USER1 == null) {

			O_TKECM_USER.setUId("TKECU0001");
		} else {

			String userId = O_TKECM_USER1.getUId();
			Integer Ag = Integer.valueOf(userId.substring(5));
			Ag++;

			System.err.println(Ag);
			O_TKECM_USER.setUId("TKECU" + String.format("%04d", Ag));
		}

		try {

			O_TKECM_USER.setUName(RO_User.getUName());
			O_TKECM_USER.setUMail(RO_User.getUMail());
			O_TKECM_USER.setUPassword(RO_User.getUPassword());
			O_TKECM_USER.setUCreatedDate(OffsetDateTime.now());
			O_TKECM_USER.setURegType("E");
			O_TKECM_USER.setUStatus("N");
			O_TKECM_USER.setUOtpStatus("N");
			O_Session.save(O_TKECM_USER);
			O_Transaction.commit();
			O_Session.close();

			System.out.println("New User Created By EmailId");
			return O_TKECM_USER.getUId();

		} catch (Exception e) {
			e.printStackTrace();
			if (O_Transaction.isActive()) {
				O_Transaction.rollback();
			}
			O_Session.close();

			return null;

		}
	}

	@Override
	public String saveOTPDetails(Integer oTP, String userId) {

		String hash = new BCryptPasswordEncoder().encode(OffsetDateTime.now().toString());

		TKECMUSER O_TKECMUSER = O_SessionFactory.getCurrentSession().get(TKECMUSER.class, userId);

		O_TKECMUSER.setUOtp(oTP);
		O_TKECMUSER.setUHashKey(hash);
		O_TKECMUSER.setUOtpExp((OffsetDateTime.now().plusMinutes(5)));

		O_SessionFactory.getCurrentSession().save(O_TKECMUSER);

		return hash;
	}

	@Override
	public User isUserPhoneNoAvailable(BigInteger phoneNo) {

		String getUserByPhone = "FROM TKECMUSER WHERE uPhone =:phoneNo";

		Query getUserByPhoneQry = O_SessionFactory.getCurrentSession().createQuery(getUserByPhone);
		getUserByPhoneQry.setParameter("phoneNo", phoneNo);

		TKECMUSER O_TKECMUSER = (TKECMUSER) getUserByPhoneQry.uniqueResult();

		if (O_TKECMUSER == null) {

			return null;

		}

		User O_User = O_ModelMapper.map(O_TKECMUSER, User.class);

//		O_PJ_TKECMUSER.setTkecmuPassword(O_TKECMUSER.getTkecmuPassword());
//		O_PJ_TKECMUSER.setTkecmuId(O_TKECMUSER.getTkecmuId());
//		O_PJ_TKECMUSER.setTkecmuStatus(O_TKECMUSER.getTkecmuStatus());
//		O_PJ_TKECMUSER.setTkecmuOtpStatus(O_TKECMUSER.getTkecmuOtpStatus());

		return O_User;

	}

	@Override
	public String createNewUserByPhoneNo(User RO_User) {
		Session O_Session = O_SessionFactory.openSession();
		Transaction O_Transaction = O_Session.beginTransaction();

		String getUserId = "FROM TKECMUSER ORDER BY uId DESC";

		Query userIdQuery = O_SessionFactory.getCurrentSession().createQuery(getUserId);
		userIdQuery.setMaxResults(1);
		TKECMUSER O_TKECM_USER1 = (TKECMUSER) userIdQuery.uniqueResult();

		TKECMUSER O_TKECM_USER = new TKECMUSER();

		if (O_TKECM_USER1 == null) {

			O_TKECM_USER.setUId("TKECU0001");
		} else {

			String userId = O_TKECM_USER1.getUId();
			Integer Ag = Integer.valueOf(userId.substring(5));
			Ag++;

			System.err.println(Ag);
			O_TKECM_USER.setUId("TKECU" + String.format("%04d", Ag));
		}

		try {

			O_TKECM_USER.setUName(RO_User.getUName());
			O_TKECM_USER.setUOtp(RO_User.getUOtp());
			O_TKECM_USER.setUPhone(RO_User.getUPhone());
			O_TKECM_USER.setUPassword(RO_User.getUPassword());
			O_TKECM_USER.setUCreatedDate(OffsetDateTime.now());
			O_TKECM_USER.setURegType("M");
			O_TKECM_USER.setUCountryCode(RO_User.getUCountryCode());
			O_TKECM_USER.setUOtpStatus("N");
			O_TKECM_USER.setUStatus("N");
			O_Session.save(O_TKECM_USER);
			O_Transaction.commit();
			O_Session.close();

			System.out.println("New User Created By PhoneNo");
			return O_TKECM_USER.getUId();

		} catch (Exception e) {
			e.printStackTrace();
			if (O_Transaction.isActive()) {
				O_Transaction.rollback();
			}
			O_Session.close();

			return null;

		}
	}

	@Override
	public User getUserDetailHash(User RO_User) {

		String getUserDetail = "FROM TKECMUSER WHERE uHashKey =:hashKey";

		Query query = O_SessionFactory.getCurrentSession().createQuery(getUserDetail);
		query.setParameter("hashKey", RO_User.getUHashKey());
		TKECMUSER O_TKECMUSER = (TKECMUSER) query.uniqueResult();

		if (O_TKECMUSER == null) {
			return null;
		}
		User O_User = O_ModelMapper.map(O_TKECMUSER, User.class);

//		O_PJ_TKECMUSER.setTkecmuOtp(O_TKECMUSER.getTkecmuOtp());
//		O_PJ_TKECMUSER.setTkecmuOtpExp(O_TKECMUSER.getTkecmuOtpExp());
//		O_PJ_TKECMUSER.setTkecmuId(O_TKECMUSER.getTkecmuId());

		return O_User;
	}

	@Override
	public void changeOTPStatus(String userId) {

		TKECMUSER O_TKECMUSER = O_SessionFactory.getCurrentSession().get(TKECMUSER.class, userId);
		O_TKECMUSER.setUStatus("Y");
		O_TKECMUSER.setUOtpStatus("Y");
		O_SessionFactory.getCurrentSession().update(O_TKECMUSER);
	}

	@Override
	public void updatePassword(User O_User_Detail) {

		TKECMUSER O_TKECMUSER = O_SessionFactory.getCurrentSession().get(TKECMUSER.class, O_User_Detail.getUId());
		O_TKECMUSER.setUPassword(O_User_Detail.getUPassword());
		O_TKECMUSER.setUModifideDate(OffsetDateTime.now());
		O_SessionFactory.getCurrentSession().update(O_TKECMUSER);

	}

	@Override
	public UserSession getApiSecretDataByNewSecret(String apisecret, String userId) {

		TKECMUSER O_TKECMUSER = O_SessionFactory.getCurrentSession().get(TKECMUSER.class, userId);

		Session O_Session = O_SessionFactory.openSession();
		Transaction O_Transaction = O_Session.beginTransaction();

		TKECTUSERSESSION O_TKECTUSERSESSION = new TKECTUSERSESSION();
		O_TKECTUSERSESSION.setUsTkecmuId(O_TKECMUSER);
		O_TKECTUSERSESSION.setUsApiKey(apisecret);
		O_TKECTUSERSESSION.setUsCreatedDate(OffsetDateTime.now());
		O_TKECTUSERSESSION.setUsAliveYN("Y");
		O_Session.save(O_TKECTUSERSESSION);

		O_Transaction.commit();
		O_Session.close();

		UserSession O_UserSession = new UserSession();

		O_UserSession.setUsApiKey(O_TKECTUSERSESSION.getUsApiKey());

		return O_UserSession;
	}

	@Override
	public void addAuditDetail(User O_USER_DETAIL, HttpServletRequest httpServletRequest) {

		TKECTUSERAUDIT O_TKECTUSERAUDIT = new TKECTUSERAUDIT();

		O_TKECTUSERAUDIT.setUaLoginTime(OffsetDateTime.now());
		O_TKECTUSERAUDIT.setUaUserAgent(httpServletRequest.getHeader("user-agent"));
		O_TKECTUSERAUDIT
				.setUaTkecmuId(O_SessionFactory.getCurrentSession().get(TKECMUSER.class, O_USER_DETAIL.getUId()));
		O_TKECTUSERAUDIT.setUaApiKey(O_USER_DETAIL.getApiKey());
		// O_TKECTUSERAUDIT.setTKECTUA_IP(httpServletRequest.getRemoteHost());

		O_SessionFactory.getCurrentSession().save(O_TKECTUSERAUDIT);

	}

	@Override
	public User getUserDetailAPIKey(String apiKey) {

		String apiQuery = "FROM TKECTUSERSESSION WHERE usApiKey =:apikey";

		Query query = O_SessionFactory.getCurrentSession().createQuery(apiQuery);
		query.setParameter("apikey", apiKey);
		TKECTUSERSESSION O_TKECTUSERSESSION = (TKECTUSERSESSION) query.uniqueResult();

		if (O_TKECTUSERSESSION == null) {
			return null;
		}
		// TKECMUSER O_TKECMUSER = O_TKECTUSERSESSION.getTkectusUserId();
		// PJ_TKECMUSER O_PJ_TKECMUSER = O_ModelMapper.map(O_TKECMUSER,
		// PJ_TKECMUSER.class);

		User O_User = new User();

		O_User.setUName(O_TKECTUSERSESSION.getUsTkecmuId().getUName());
		O_User.setUMail(O_TKECTUSERSESSION.getUsTkecmuId().getUMail());
		O_User.setUPhone(O_TKECTUSERSESSION.getUsTkecmuId().getUPhone());
		O_User.setUPassword(O_TKECTUSERSESSION.getUsTkecmuId().getUPassword());
		O_User.setUId(O_TKECTUSERSESSION.getUsTkecmuId().getUId());
		return O_User;
	}

	@Override
	public Boolean userLogout(String apiKey) {
		Session O_Session = O_SessionFactory.openSession();
		Transaction O_Transaction = O_Session.beginTransaction();

		String getApiKey = "FROM TKECTUSERSESSION WHERE usApiKey =:apiKey";

		try {

			Query getApiKeyQuery = O_Session.createQuery(getApiKey);
			getApiKeyQuery.setParameter("apiKey", apiKey);
			TKECTUSERSESSION O_TKECTUSERSESSION = (TKECTUSERSESSION) getApiKeyQuery.uniqueResult();

			O_TKECTUSERSESSION.setUsAliveYN("N");

			O_Session.update(O_TKECTUSERSESSION);

			String getUserId1 = "FROM TKECTUSERAUDIT WHERE uaApiKey =:apikey ";

			Query query1 = O_Session.createQuery(getUserId1);
			query1.setParameter("apikey", apiKey);

			TKECTUSERAUDIT O_TKECTUSERAUDIT = (TKECTUSERAUDIT) query1.uniqueResult();
			O_TKECTUSERAUDIT.setUaLogoutTime((OffsetDateTime.now()));
			O_Session.update(O_TKECTUSERAUDIT);

			O_Transaction.commit();
			O_Session.close();

		}

		catch (Exception e) {

			e.printStackTrace();
			if (O_Transaction.isActive()) {
				O_Transaction.rollback();
				O_Session.close();
			}
			return false;
		}
		return true;
	}
}

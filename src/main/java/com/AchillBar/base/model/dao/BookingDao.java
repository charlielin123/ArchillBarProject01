package com.AchillBar.base.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.AchillBar.base.model.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {
        @Query("from Booking where m_id=?1")
        public List<Booking> findByM_id(Integer m_id);

        @Query("from Booking where m_id=?1 order by bookDate desc")
        public List<Booking> findByM_id2(Integer m_id);

        public List<Booking> findByBookDate(Date date);

        @Query(value = "from Booking where tableNum=?1 and bookDate=?2")
        public List<Booking> findByTableNumAndDate(Integer tableNum, Date date);

        @Query(value = "from Booking where m_id=?1 and bookDate=?2")
        public List<Booking> findByMidAndDate(Integer m_id, Date date);

        @Transactional
        @Modifying
        @Query(value = "update Booking b set b.status =?2 where b.b_id=?1")
        public void editBookingStatusById(Long b_id, Integer status);

        @Query("from Booking where b_id=?1")
        public List<Booking> findByb_id(Long b_id);

        @Query(value = "select b.b_id,o.updateDate,m.m_id,m.m_name,o.o_id,b.bookDate,os.unit,os.quantity,os.p_id\r\n"
                        + "  from (Booking b join [Order] o \r\n"
                        + "   on b.b_id =o.b_id) join Member m \r\n"
                        + "   on b.m_id =m.m_id join Orderdetails os\r\n"
                        + "   on os.o_id=o.o_id", nativeQuery = true)
        public List<Map<String, Object>> findOrderTotal();

        @Query(value = "select b.b_id,o.updateDate,m.m_id,m.m_name,o.o_id,b.bookDate "
                        + "from (Booking b join [Order] o "
                        + "on b.b_id =o.b_id) join Member m "
                        + "on b.m_id =m.m_id "
                        + "order by o_id DESC ", nativeQuery = true)
        public List<Map<String, Object>> findOrderByOid();

        @Query(value = "from Booking b Where b_id=?1 order by b.bookDate")
        public Booking findcheckedByBid(Long b_id);

        @Query(value = " select b.bookDate from Booking b order by b.bookDate desc", nativeQuery = true)
        public Booking descbookdate();

        @Query(value = "select p.p_name, SUM(quantity) quantity,MAX(unit) unit,(SUM(quantity)*MAX(unit))subTotal " +
                        "from ((Booking b inner join [Order] o " +
                        "on b.b_id=o.b_id) " +
                        "inner join Orderdetails odd " +
                        "on o.o_id=odd.o_id) inner join Product p " +
                        "on p.p_id=odd.p_id " +
                        "where b.tableNum=?1 and bookDate=?2 " +
                        "group by p.p_name ", nativeQuery = true)
        public List<Map<String, Object>> getCheckByDateAndTable(Integer tableNum, Date date);

        @Query(value = "from Booking b inner join b.order o where b.status<3 and b.b_id=o.b_id")
        public Page<Booking> orderpage(Pageable pageable);

        @Query(value = "select * from Booking where m_id=?1 and status<3 and bookDate>=CONVERT (date, GETDATE())", nativeQuery = true)
        public List<Booking> findByMidAfterToday(Integer m_id);

        @Query(value = "from Booking b inner join b.order o where b.status=3 and b.b_id=o.b_id")
        public Page<Booking> pastorder(Pageable pageable);

        @Query(value = " select bookDate  "
                        + " from  Booking b join [Order] o"
                        + " on b.b_id=o.b_id "
                        + " where status<3 "
                        + " group by bookDate ", nativeQuery = true)
        public List<Map<String, Date>> groupbybookdate();

        @Query("from Booking  where bookDate= ?1 ")
        public List<Booking> findbydate(Date date);

        @Query(value = " select b from Booking b inner join b.member m  where m_name like %?1% and status =3 ")
        public Page<Booking> findnameLike(String name, Pageable pageable);

}

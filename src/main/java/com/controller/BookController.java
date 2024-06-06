package com.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.entity.*;
import com.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.view.BookView;

import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 物资出库
 * 后端接口
 * @author 
 * @email 
 * @date 2022-07-24 20:15:33
 */
@RestController
@RequestMapping("/home")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookBooklbService bookBooklbService;

    @Autowired
    private BookActorService bookActorService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private BookUserService  bookUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private BooklbService booklbService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/anload")
    public R page(@RequestParam Integer id,
                  HttpServletRequest request){

        EntityWrapper<BookBooklbEntity> ew1 = new EntityWrapper<>();
        ew1.eq("booklbid", id);
        List<BookBooklbEntity> bookBooklbEntities = bookBooklbService.selectList(ew1);
        // 提取bookid list集合
        List<Integer> collect = bookBooklbEntities.stream().map(BookBooklbEntity::getBookid).collect(Collectors.toList());
        EntityWrapper<BookEntity> ew = new EntityWrapper<>();
        ew.in("id", collect);
        List<BookEntity> bookEntities = bookService.selectList(ew);
        List<BookView> bookViews = new ArrayList<>();
        bookEntities.forEach(bookEntity -> {
            BookView bookView = new BookView();
            BeanUtils.copyProperties(bookEntity, bookView);
            EntityWrapper<BookActorEntity> ew2 = new EntityWrapper<>();
            EntityWrapper<ActorEntity> ew3 = new EntityWrapper<>();
            ew2.eq("bookid", bookEntity.getId());
            BookActorEntity bookActor = bookActorService.selectOne(ew2);
            ew3.eq("id", bookActor.getActorid());
            ActorEntity actorEntity = actorService.selectOne(ew3);
            bookView.setActor(actorEntity.getName());
            bookViews.add(bookView);
        });

        return R.ok().put("data", bookViews);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/bookload")
    public R list(@RequestParam Integer id,
                  HttpServletRequest request){
        EntityWrapper<BookEntity> ew = new EntityWrapper<>();
        ew.eq("id", id);
        BookEntity bookEntity = bookService.selectOne(ew);
        BookView bookView = new BookView();
        BeanUtils.copyProperties(bookEntity, bookView);
        EntityWrapper<BookActorEntity> ew2 = new EntityWrapper<>();
        EntityWrapper<ActorEntity> ew3 = new EntityWrapper<>();
        ew2.eq("bookid", bookEntity.getId());
        BookActorEntity bookActor = bookActorService.selectOne(ew2);
        ew3.eq("id", bookActor.getActorid());
        ActorEntity actorEntity = actorService.selectOne(ew3);
        bookView.setActor(actorEntity.getName());
        return R.ok().put("data", bookView);
    }

	/**
     * 列表
     */
    @RequestMapping("/load")
    public R list(@RequestParam(required = false) String keyword,@RequestParam Integer uid){

        EntityWrapper<BookEntity> ew = new EntityWrapper<>();
        if(StringUtils.isNotBlank(keyword)){
            ew.like("name", keyword);
        }
        List<BookEntity> bookEntities = bookService.selectList(ew);
        List<BookView> bookViews = new ArrayList<>();
        EntityWrapper<BookUserEntity> ew1 = new EntityWrapper<>();
        ew1.eq("userid", uid);
        List<BookUserEntity> bookUserEntities = bookUserService.selectList(ew1);
        List<Integer> collect = new ArrayList<>();
        if(!CollectionUtils.isEmpty(bookUserEntities)) {
            collect = bookUserEntities.stream().map(BookUserEntity::getBookid).collect(Collectors.toList());
        }
        List<Integer> finalCollect = collect;
        bookEntities.forEach(bookEntity -> {
            if(!finalCollect.contains(bookEntity.getId())) {
                BookView bookView = new BookView();
                BeanUtils.copyProperties(bookEntity, bookView);
                EntityWrapper<BookActorEntity> ew2 = new EntityWrapper<>();
                EntityWrapper<ActorEntity> ew3 = new EntityWrapper<>();
                ew2.eq("bookid", bookEntity.getId());
                BookActorEntity bookActor = bookActorService.selectOne(ew2);
                ew3.eq("id", bookActor.getActorid());
                ActorEntity actorEntity = actorService.selectOne(ew3);
                bookView.setActor(actorEntity.getName());
                bookViews.add(bookView);
            }
        });

        return R.ok().put("data", bookViews);
    }


    /**
     * 分页列表
     */
    @RequestMapping("/page")
    public R list(@RequestBody BookView bookView){
        EntityWrapper<BookEntity> ew = new EntityWrapper<>();
        if(StringUtils.isNotBlank(bookView.getName())){
            ew.like("name", bookView.getName());
        }
        Page<BookEntity> page = new Page<>(bookView.getPage(),10);
        Page<BookView> result = new Page<>(bookView.getPage(),10);
        Page<BookEntity> bookEntities = bookService.selectPage(page, ew);
        List<BookView> bookViews = new ArrayList<>();
        bookEntities.getRecords().forEach(bookEntity -> {
                BookView view = new BookView();
            BeanUtils.copyProperties(bookEntity, view);
            EntityWrapper<BookActorEntity> ew2 = new EntityWrapper<>();
            EntityWrapper<ActorEntity> ew3 = new EntityWrapper<>();
            ew2.eq("bookid", bookEntity.getId());
            BookActorEntity bookActor = bookActorService.selectOne(ew2);
            if(bookActor != null) {
                ew3.eq("id", bookActor.getActorid());
                ActorEntity actorEntity = actorService.selectOne(ew3);
                view.setActor(actorEntity.getName());
            }
            EntityWrapper<BookBooklbEntity> ew1 = new EntityWrapper<>();
            ew1.eq("bookid", bookEntity.getId());
            BookBooklbEntity bookBooklbEntity = bookBooklbService.selectOne(ew1);
            if(bookBooklbEntity != null) {
                view.setCategoryId(bookBooklbEntity.getBooklbid());
                EntityWrapper<BooklbEntity> ew5 = new EntityWrapper<>();
                ew5.eq("id", bookBooklbEntity.getBooklbid());
                BooklbEntity booklbEntity = booklbService.selectOne(ew5);
                view.setCategoryName(booklbEntity.getName());
            }
            bookViews.add(view);
        });
        result.setRecords(bookViews);
        result.setTotal(bookEntities.getTotal());
        return R.ok().put("data", result);
    }


    /**
     * 查询
     */
    @RequestMapping("/shoucang")
    public R query(@RequestBody BookUserEntity bookUser){
        EntityWrapper<BookUserEntity> ew = new EntityWrapper<>();
        EntityWrapper<BookUserEntity> ew1 = new EntityWrapper<>();
        ew.eq("userid", bookUser.getUserid());
        ew1.eq("userid", bookUser.getUserid());

        // 当前日期
        LocalDate currentDate = LocalDate.now();
        Date date = new Date();
        // 当月第一天
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        // 当月最后一天
        LocalDate lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
        ew.between("borrow_date", firstDayOfMonth, lastDayOfMonth);
        int num = bookUserService.selectCount(ew);
        ew1.eq("bookid", bookUser.getBookid());
        BookUserEntity bookUserEntity = bookUserService.selectOne(ew1);
//        if(bookUserEntity != null) {
//            num = num - 1;
//        }
        UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("id", bookUser.getUserid()));
        Integer age = user.getAge();
        Integer limit = 0;
        if(age >= 18){
            limit = 3;
        } else {
            limit = 5;
        }
        if(user.getIfvip() == 1){
            Date deadline = user.getDeadline();
            if(date.after(deadline)){
                if(num >= limit){
                    return R.error(1, "會員已過期，超出借閲次數");
                }
            }
        } else {
            if (num >= limit) {
                return R.error(2, "超出本月借阅次数，请开通会员");
            }
        }
        if(bookUserEntity != null){
            bookUserEntity.setBorrowDate(date);
            bookUserService.updateById(bookUserEntity);
        }else {
            bookUser.setBorrowDate(date);
            bookUserService.insert(bookUser);
        }
        return R.ok("借閲成功");
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("update")
    public R info(@RequestBody BookView bookView){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookView, bookEntity);
        bookService.updateById(bookEntity);
        EntityWrapper<BookBooklbEntity> ew = new EntityWrapper<>();
        ew.eq("bookid", bookEntity.getId());
        bookBooklbService.delete(ew);
        BookBooklbEntity bookBooklbEntity = new BookBooklbEntity();
        bookBooklbEntity.setBookid(bookView.getId().intValue());
        bookBooklbEntity.setBooklbid(bookView.getCategoryId());
        bookBooklbService.insert(bookBooklbEntity);
        return R.ok();
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BookEntity wuzichuku = bookService.selectById(id);
        return R.ok().put("data", wuzichuku);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BookView bookView, HttpServletRequest request){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(bookView, bookEntity);
    	//ValidatorUtils.validateEntity(wuzichuku);
        bookService.insert(bookEntity);
        BookBooklbEntity bookBooklbEntity = new BookBooklbEntity();
        bookBooklbEntity.setBookid(bookEntity.getId().intValue());
        bookBooklbEntity.setBooklbid(bookView.getCategoryId());
        bookBooklbService.insert(bookBooklbEntity);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BookEntity wuzichuku, HttpServletRequest request){
    	wuzichuku.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(wuzichuku);
        bookService.insert(wuzichuku);
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam Integer uid, @RequestParam Integer bid ){
        Wrapper<BookUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("userid", uid);
        wrapper.eq("bookid", bid);
        bookUserService.delete(wrapper);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/deleteBook")
    public R deleteBook(@RequestParam Integer bid ){
        Wrapper<BookEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("id", bid);
        bookService.delete(wrapper);
        return R.ok();
    }
    
    /**
     * 书架接口
     */
	@RequestMapping("/bookRacks")
	public R remindCount(@RequestParam Integer uid) {
        List<BookView> bookViews = new ArrayList<>();
        Date date = new Date();
        EntityWrapper<BookUserEntity> ew = new EntityWrapper<>();
        ew.eq("userid", uid);
        List<BookUserEntity> bookUserEntities = bookUserService.selectList(ew);
        for (BookUserEntity bookUserEntity : bookUserEntities) {
            BookView bookView = new BookView();
            bookViews.add(bookView);
            Integer bookid = bookUserEntity.getBookid();
            EntityWrapper<BookEntity> ew1 = new EntityWrapper<>();
            ew1.eq("id", bookid);
            BookEntity bookEntity = bookService.selectOne(ew1);
            BeanUtils.copyProperties(bookEntity, bookView);
            Date borrowDate = bookUserEntity.getBorrowDate();
            if(borrowDate != null){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(borrowDate);
                // 在当前日期基础上加一个月
                calendar.add(Calendar.MONTH, 1);
                // 获取一个月后的日期
                Date nextMonthDate = calendar.getTime();
                if(date.after(nextMonthDate)){
                    bookView.setMature(1);
                }else{
                    bookView.setMature(0);
                }
            }
        }
		return R.ok().put("data", bookViews);
	}
	







}

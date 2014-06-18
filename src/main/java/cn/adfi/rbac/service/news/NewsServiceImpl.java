/**
 * 
 */
package cn.adfi.rbac.service.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.ISearch;

import cn.adfi.rbac.dao.news.INewsDao;
import cn.adfi.rbac.model.News;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-4
 */
@Service
@Transactional
public class NewsServiceImpl implements INewsService {
	
	INewsDao newsDao;
	
	@Autowired
	void setNewsDao(INewsDao newsDao){
		this.newsDao = newsDao;
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.news.INewsService#findAll()
	 */
	@Override
	public List<News> findAll() {
		return  newsDao.findAll();
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.news.INewsService#find(java.lang.Long)
	 */
	@Override
	public News find(Long id) {
		return newsDao.find(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.news.INewsService#save(cn.adfi.rbac.entity.News)
	 */
	@Override
	public boolean save(News entity) {
		return newsDao.save(entity);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.news.INewsService#removeById(java.lang.Long)
	 */
	@Override
	public boolean removeById(Long id) {
		return newsDao.removeById(id);
	}

	/* (non-Javadoc)
	 * @see cn.adfi.rbac.service.news.INewsService#count()
	 */
	@Override
	public int count(ISearch search) {
		return newsDao.count(search);
	}
	
	
}

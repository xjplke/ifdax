/**
 * 
 */
package cn.adfi.rbac.service.news;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;

import cn.adfi.rbac.model.News;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-4
 */
public interface INewsService {
	public List<News> findAll();
	public News find(Long id);
	public boolean save(News entity);
	public boolean removeById(Long id);
	public int count(ISearch search);
}

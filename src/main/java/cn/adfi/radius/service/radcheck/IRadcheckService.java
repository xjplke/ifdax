/**
 * 
 */
package cn.adfi.radius.service.radcheck;

import java.util.List;

import com.googlecode.genericdao.search.ISearch;

import cn.adfi.radius.model.Radcheck;

/**
 * @author shaojunwu  --sjw
 * @date 2014-5-15
 */
public interface IRadcheckService {
	public Radcheck searchUnique(ISearch search);
	public List<Radcheck> search(ISearch search);
}

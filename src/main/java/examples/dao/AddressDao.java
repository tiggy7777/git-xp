package examples.dao;

/**
 * Created with IntelliJ IDEA.
 * User: adrien
 * Date: 8/05/12
 * Time: 0:10
 * To change this template use File | Settings | File Templates.
 */

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import examples.model.Address;

@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhcacheCache.class)
public interface AddressDao {

	String GET_ADDRESS_BY_ID = "SELECT * FROM vw_address WHERE id = #{addressId}";
	String INSERT_ADDRESS	 = "INSERT into address (building,street,location,town,postCode,countyId,countryId,notes,createdOn,createdBy,active) VALUES (#{building},#{street},#{location},#{town},#{postCode},#{countyId},#{countryId},#{notes},sysdate(),#{createdBy},1)";
	String UPDATE_ADDRESS    = "UPDATE address set building=#{building},countyId=#{countyId}, street=#{street},location=#{location},town=#{town},postCode=#{postCode},notes=#{notes},modifiedOn=sysdate(),modifiedBy=#{modifiedBy},countryId=#{countryId} where id= #{id}";
	String DELETE_ADDRESS    = "DELETE from address WHERE id = #{addressId}";

	@Select(GET_ADDRESS_BY_ID)
	@Options(useCache=true)
	public Address doSelectAddress(long addressId) throws Exception;

	@Insert(INSERT_ADDRESS)
	@Options(useGeneratedKeys = true, keyProperty = "id", flushCache=true)
	public int doCreateAddress(Address address) throws Exception;

	@Update(UPDATE_ADDRESS)
	@Options(flushCache=true)
	public int doUpdateAddress(Address address) throws Exception;

	@Delete(DELETE_ADDRESS)
	@Options(flushCache=true)
	public int doDeleteAddress(long addressId) throws Exception;

}


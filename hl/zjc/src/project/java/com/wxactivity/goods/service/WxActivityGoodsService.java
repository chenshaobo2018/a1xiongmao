/**
 * 
 */
package com.wxactivity.goods.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aos.framework.core.dao.SqlDao;
import aos.framework.core.typewrap.Dto;
import aos.framework.core.typewrap.Dtos;
import aos.framework.core.utils.AOSJson;
import aos.framework.core.utils.AOSUtils;
import aos.framework.web.router.HttpModel;

import com.api.ApiPublic.Apiconstant;
import com.api.common.po.MessageVO;
import com.api.common.po.PageVO;
import com.api.common.util.ConstantUtil;
import com.api.common.util.ParameterUtil;
import com.zjc.goods.dao.ZjcSpecGoodsPriceDao;
import com.zjc.goods.dao.po.ZjcGoodsPO;
import com.zjc.goods.dao.po.ZjcSpecItemPO;
import com.zjc.goods.dao.po.ZjcSpecPO;
import com.zjc.goodsImage.dao.po.ZjcGoodsImagesPO;
import com.zjc.users.dao.ZjcUserAddressDao;
import com.zjc.users.dao.ZjcUsersInfoDao;
import com.zjc.users.dao.po.ZjcUserAddressPO;
import com.zjc.users.dao.po.ZjcUsersInfoPO;

/**
 * @author Administrator
 *
 */
@Service(value = "wxActivityGoodsService")
public class WxActivityGoodsService {

	@Autowired
	private SqlDao sqlDao;
	@Autowired
	private ZjcSpecGoodsPriceDao zjcSpecGoodsPriceDao;
	@Autowired
	private ZjcUsersInfoDao zjcUsersInfoDao;
	@Autowired
	private ZjcUserAddressDao zjcUserAddressDao;

	/**
	 * 查询分页商品列表
	 * 
	 * @param httpModel
	 * @return
	 */
	public PageVO getWxGoods(HttpModel httpModel) {
		MessageVO MessageVO = new MessageVO();
		Dto qDto = httpModel.getInDto();
		// 设置limit每页条数
		qDto.put("limit", ConstantUtil.pageSize);
		// 设置start开始条数
		Object b = httpModel.getAttribute("page");
		String a = String.valueOf(b);
		int page = Integer.parseInt((String) a);
		qDto.put("start", (page - 1) * ConstantUtil.pageSize);
		List<ZjcGoodsPO> goodslist = sqlDao.list(
				"com.zjc.goods.dao.ZjcGoodsDao.goodsWxStorePage", qDto);
		PageVO pageVO = new PageVO();
		if (goodslist.size() == 0) {
			MessageVO.setCode(Apiconstant.NO_DATA.getIndex());
			MessageVO.setMsg(Apiconstant.NO_DATA.getName());
		} else {
			// 生成返回分页参数实体
			pageVO = ParameterUtil.getPageVO(qDto.getInteger("total"), 1);
			pageVO.setList(goodslist);
			pageVO.setNowPage(page);
		}
		return pageVO;
	}

	public ZjcGoodsPO getGoodsDetailByGoodsId(Dto inDto) {

		// 根据商品ID查询商品详情信息
		if (AOSUtils.isEmpty(inDto.getString("goods_id"))) {
			return null;
		}
		ZjcGoodsPO goodDetails = (ZjcGoodsPO) sqlDao.selectOne(
				"com.zjc.goods.dao.ZjcGoodsDao.GoodsDetailed", inDto);
		if (goodDetails != null) {
			goodDetails.setZjcGoodsImagesPO(null);
			ZjcGoodsImagesPO imgPO = new ZjcGoodsImagesPO();
			imgPO.setImage_url(goodDetails.getOriginal_img());
			List<ZjcGoodsImagesPO> imgsList = new ArrayList<ZjcGoodsImagesPO>();
			imgsList.add(imgPO);
			goodDetails.setZjcGoodsImagesPO(imgsList);
			String aduit_info = goodDetails.getAduit_info();
			if (!AOSUtils.isEmpty(aduit_info)) {
				String[] aduit_infoArr = aduit_info.split(";");
				String str = aduit_infoArr[5];
				String aduit_user_name = str.substring(str.indexOf("\"") + 1,
						str.indexOf("\"", str.indexOf("\"") + 1));
				goodDetails.setAduit_user_name(aduit_user_name);
			}
			// 通过goods_id查询规格项字符串
			String specStr = zjcSpecGoodsPriceDao.getSpecStr(inDto);
			if (!AOSUtils.isEmpty(specStr)) {
				specStr = specStr.replace("_", ",");
				String strSql = " and id in (" + specStr + ")";
				inDto.put("strSql", strSql);
				// 查询该商品的符合条件的规格项数据
				List<ZjcSpecItemPO> itemList = sqlDao.list(
						"com.zjc.goods.dao.ZjcSpecItemDao.getSpecItem", inDto);
				// 查询商品规格
				String sqlStr = " and t1.id in (" + specStr + ")";
				inDto.put("sqlStr", sqlStr);
				// 查询该商品的规格数据
				List<ZjcSpecPO> specList = sqlDao.list(
						"com.zjc.goods.dao.ZjcSpecDao.getSpecs", inDto);
				if (!AOSUtils.isEmpty(itemList) || !AOSUtils.isEmpty(specList)) {
					for (ZjcSpecPO specPO : specList) {// 规格项分组
						List<ZjcSpecItemPO> newItemList = new ArrayList<ZjcSpecItemPO>();
						for (ZjcSpecItemPO specItemPO : itemList) {
							if (specPO.getId() == specItemPO.getSpec_id()) {// 如果规格数据ZjcSpecPO的ID等于规格项数据ZjcSpecItemPO的spec_id
								newItemList.add(specItemPO);// 追加到List,分组
							}
						}
						specPO.setZjcSpecItemPOList(newItemList);
					}
					goodDetails.setZjcSpecPOList(specList);
				}
			}
		}

		return goodDetails;
	}
	
	/**
	 * 收货地址
	 * 
	 * @param openid
	 * @return
	 */
	public String getUserAddressList(String openid){
		MessageVO MessageVO=new MessageVO();
		if(!openid.isEmpty()){
			List<ZjcUserAddressPO> address = new ArrayList<ZjcUserAddressPO>();
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid", openid));
			address = zjcUserAddressDao.list(Dtos.newDto("user_id", zjcUsersInfoPO.getUser_id()));
			MessageVO.setCode(Apiconstant.Do_Success.getIndex());
			MessageVO.setMsg(Apiconstant.Do_Success.getName());
			MessageVO.setData(address);
		}else{
			MessageVO.setCode(Apiconstant.System_busy.getIndex());
			MessageVO.setMsg(Apiconstant.System_busy.getName());
		}
		return AOSJson.toJson(MessageVO);
	} 
	
	/**
	 * 新增地址
	 * 
	 * @param dto
	 * @return
	 */
	public String addNewAdress(Dto dto){
		MessageVO MessageVO=new MessageVO();
		ZjcUserAddressPO newAdress = new ZjcUserAddressPO();
		if(dto.getString("openid") != null && dto.getString("openid") != ""){
			ZjcUsersInfoPO zjcUsersInfoPO = zjcUsersInfoDao.selectOne(Dtos.newDto("openid",dto.getString("openid")));
			//先将以前的默认地址设置为非默认
			Dto qAddress = Dtos.newDto();
			qAddress.put("user_id", zjcUsersInfoPO.getUser_id());
			qAddress.put("is_default", 1);
			ZjcUserAddressPO oldDefaultAdress = zjcUserAddressDao.selectOne(qAddress);
			if(oldDefaultAdress != null){
				oldDefaultAdress.setIs_default(0);
				zjcUserAddressDao.updateByKey(oldDefaultAdress);
			}
			//添加新地址
			AOSUtils.copyProperties(dto, newAdress);
			newAdress.setUser_id(zjcUsersInfoPO.getUser_id());
			newAdress.setAddress_info(dto.getString("area_info") + dto.getString("address"));
			newAdress.setIs_default(1);//新增地址设置为默认地址
			int row = zjcUserAddressDao.insert(newAdress);
			if(row == 1){
				MessageVO.setCode(Apiconstant.Do_Success.getIndex());
				MessageVO.setMsg(Apiconstant.Do_Success.getName());
			}else{
				MessageVO.setCode(Apiconstant.Save_fails.getIndex());
				MessageVO.setMsg(Apiconstant.Save_fails.getName());
			}
		}else {
			MessageVO.setCode(Apiconstant.OPEN_ID_FAILS.getIndex());
			MessageVO.setMsg(Apiconstant.OPEN_ID_FAILS.getName());
		}
		return AOSJson.toJson(MessageVO);
	}

}

/**
 * 
 */
package com.api.cfca.service;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cpcn.institution.tools.util.Base64;
import cpcn.institution.tools.util.StringUtil;
import payment.api.system.CMBEnvironment;
import payment.api.system.CrossBorderEnvironment;
import payment.api.system.Gateway4ConsumerEnvironment;
import payment.api.system.Gateway4DepositBankEnvironment;
import payment.api.system.Gateway4FileEnvironment;
import payment.api.system.Gateway4POSEnvironment;
import payment.api.system.PaymentUserEnvironment;
import payment.api.system.TxMessenger;
import payment.api.tx.TxBaseResponse;
import payment.api.tx.accountvalidation.Tx2310Response;
import payment.api.tx.accountvalidation.Tx2320Response;
import payment.api.tx.accountvalidation.Tx2340Response;
import payment.api.tx.accountvalidation.Tx2341Response;
import payment.api.tx.accountvalidation.Tx2342Response;
import payment.api.tx.accountvalidation.Tx2354Response;
import payment.api.tx.accountvalidation.Tx2392Response;
import payment.api.tx.auth.Tx1713Response;
import payment.api.tx.auth.Tx1721Response;
import payment.api.tx.auth.Tx1723Response;
import payment.api.tx.auth.Tx1731Response;
import payment.api.tx.auth.Tx1733Response;
import payment.api.tx.auth.Tx1741Response;
import payment.api.tx.bankcorp.Tx4510Response;
import payment.api.tx.bankcorp.Tx4512Response;
import payment.api.tx.bankcorp.Tx4514Response;
import payment.api.tx.bankcorp.Tx4516Response;
import payment.api.tx.bankcorp.Tx4524Response;
import payment.api.tx.bankcorp.Tx4526Response;
import payment.api.tx.bankcorp.Tx4528Response;
import payment.api.tx.bankcorp.Tx4530Response;
import payment.api.tx.bankcorp.Tx4532Response;
import payment.api.tx.bankcorp.Tx4534Response;
import payment.api.tx.bankcorp.Tx4536Response;
import payment.api.tx.bankcorp.Tx4538Response;
import payment.api.tx.bankcorp.Tx4540Response;
import payment.api.tx.bankcorp.Tx4542Response;
import payment.api.tx.bankcorp.Tx4544Response;
import payment.api.tx.bankcorp.Tx4546Response;
import payment.api.tx.cb.Tx5511Response;
import payment.api.tx.cb.Tx5512Response;
import payment.api.tx.cb.Tx5513Response;
import payment.api.tx.cb.Tx5514Response;
import payment.api.tx.cb.Tx5515Response;
import payment.api.tx.cb.Tx5516Response;
import payment.api.tx.cb.Tx5611Response;
import payment.api.tx.cb.Tx5612Response;
import payment.api.tx.cb.Tx5616Response;
import payment.api.tx.cb.Tx5617Response;
import payment.api.tx.cb.Tx5618Response;
import payment.api.tx.cb.Tx5621Response;
import payment.api.tx.cb.Tx5622Response;
import payment.api.tx.cb.Tx5623Response;
import payment.api.tx.cb.Tx5624Response;
import payment.api.tx.cb.Tx5626Response;
import payment.api.tx.cb.Tx5631Response;
import payment.api.tx.cb.Tx5632Response;
import payment.api.tx.cb.Tx5633Response;
import payment.api.tx.cb.Tx5635Response;
import payment.api.tx.cb.Tx5636Response;
import payment.api.tx.cb.Tx5637Response;
import payment.api.tx.cb.Tx5641Response;
import payment.api.tx.cb.Tx5642Response;
import payment.api.tx.cmb.Tx7111Response;
import payment.api.tx.cmb.Tx7112Response;
import payment.api.tx.cmb.Tx7122Response;
import payment.api.tx.cmb.Tx7131Response;
import payment.api.tx.cmb.Tx7132Response;
import payment.api.tx.cmb.Tx7141Response;
import payment.api.tx.cmb.Tx7142Response;
import payment.api.tx.cmb.Tx7152Response;
import payment.api.tx.cmb.Tx7201Response;
import payment.api.tx.cmb.Tx7202Response;
import payment.api.tx.cmb.Tx7203Response;
import payment.api.tx.cmb.Tx7213Response;
import payment.api.tx.cmb.Tx7214Response;
import payment.api.tx.cmb.Tx7215Response;
import payment.api.tx.cmb.Tx7216Response;
import payment.api.tx.cmb.Tx7220Response;
import payment.api.tx.cmb.Tx7223Response;
import payment.api.tx.cmb.Tx7229Response;
import payment.api.tx.cmb.Tx7230Response;
import payment.api.tx.cmb.Tx7231Response;
import payment.api.tx.cmb.Tx7232Response;
import payment.api.tx.cmb.Tx7233Response;
import payment.api.tx.cmb.Tx7234Response;
import payment.api.tx.cmb.Tx7242Response;
import payment.api.tx.cmb.Tx7244Response;
import payment.api.tx.cmb.Tx7246Response;
import payment.api.tx.cmb.Tx7247Response;
import payment.api.tx.cmb.Tx7249Response;
import payment.api.tx.cmb.Tx724xResponse;
import payment.api.tx.cmb.Tx7252Response;
import payment.api.tx.cmb.Tx7253Response;
import payment.api.tx.cmb.Tx7254Response;
import payment.api.tx.cmb.Tx7256Response;
import payment.api.tx.cmb.Tx7257Response;
import payment.api.tx.cmb.Tx7261Response;
import payment.api.tx.cmb.Tx7262Response;
import payment.api.tx.cmb.Tx7264Response;
import payment.api.tx.cmb.Tx7265Response;
import payment.api.tx.cmb.Tx7267Response;
import payment.api.tx.cmb.Tx7271Response;
import payment.api.tx.cmb.Tx7272Response;
import payment.api.tx.cmb.Tx7273Response;
import payment.api.tx.cmb.Tx7275Response;
import payment.api.tx.cmb.Tx7276Response;
import payment.api.tx.cmb.Tx7277Response;
import payment.api.tx.cmb.Tx7278Response;
import payment.api.tx.cmb.Tx7281Response;
import payment.api.tx.cmb.Tx7282Response;
import payment.api.tx.cmb.Tx7283Response;
import payment.api.tx.cmb.Tx7284Response;
import payment.api.tx.consumptionfinancial.Tx8101Response;
import payment.api.tx.consumptionfinancial.Tx8112Response;
import payment.api.tx.consumptionfinancial.Tx8113Response;
import payment.api.tx.consumptionfinancial.Tx8122Response;
import payment.api.tx.consumptionfinancial.Tx8123Response;
import payment.api.tx.consumptionfinancial.Tx8132Response;
import payment.api.tx.consumptionfinancial.Tx8133Response;
import payment.api.tx.consumptionfinancial.Tx8134Response;
import payment.api.tx.consumptionfinancial.Tx8142Response;
import payment.api.tx.consumptionfinancial.Tx8143Response;
import payment.api.tx.consumptionfinancial.Tx8145Response;
import payment.api.tx.consumptionfinancial.Tx8210Response;
import payment.api.tx.consumptionfinancial.Tx8211Response;
import payment.api.tx.consumptionfinancial.Tx8212Response;
import payment.api.tx.consumptionfinancial.Tx8213Response;
import payment.api.tx.consumptionfinancial.Tx8215Response;
import payment.api.tx.consumptionfinancial.Tx8220Response;
import payment.api.tx.consumptionfinancial.Tx8221Response;
import payment.api.tx.consumptionfinancial.Tx8222Response;
import payment.api.tx.consumptionfinancial.Tx8223Response;
import payment.api.tx.consumptionfinancial.Tx8230Response;
import payment.api.tx.consumptionfinancial.Tx8232Response;
import payment.api.tx.consumptionfinancial.Tx8242Response;
import payment.api.tx.consumptionfinancial.Tx8245Response;
import payment.api.tx.consumptionfinancial.Tx8246Response;
import payment.api.tx.consumptionfinancial.Tx8250Response;
import payment.api.tx.consumptionfinancial.Tx8251Response;
import payment.api.tx.consumptionfinancial.Tx8252Response;
import payment.api.tx.consumptionfinancial.Tx8253Response;
import payment.api.tx.consumptionfinancial.Tx8255Response;
import payment.api.tx.consumptionfinancial.Tx8260Response;
import payment.api.tx.consumptionfinancial.Tx8262Response;
import payment.api.tx.consumptionfinancial.Tx8270Response;
import payment.api.tx.consumptionfinancial.Tx8272Response;
import payment.api.tx.contractsign.Tx2711Response;
import payment.api.tx.contractsign.Tx2713Response;
import payment.api.tx.crossborder.Tx5111Response;
import payment.api.tx.crossborder.Tx5116Response;
import payment.api.tx.crossborder.Tx5126Response;
import payment.api.tx.depositbank.Tx4701Response;
import payment.api.tx.depositbank.Tx4702Response;
import payment.api.tx.depositbank.Tx4704Response;
import payment.api.tx.depositbank.Tx4705Response;
import payment.api.tx.depositbank.Tx4711Response;
import payment.api.tx.depositbank.Tx4721Response;
import payment.api.tx.depositbank.Tx4722Response;
import payment.api.tx.depositbank.Tx4731Response;
import payment.api.tx.depositbank.Tx4732Response;
import payment.api.tx.depositbank.Tx4733Response;
import payment.api.tx.depositbank.Tx4734Response;
import payment.api.tx.depositbank.Tx4736Response;
import payment.api.tx.depositbank.Tx4742Response;
import payment.api.tx.depositbank.Tx4744Response;
import payment.api.tx.depositbank.Tx4751Response;
import payment.api.tx.depositbank.Tx4752Response;
import payment.api.tx.depositbank.Tx4761Response;
import payment.api.tx.depositbank.Tx4762Response;
import payment.api.tx.depositbank.Tx4771Response;
import payment.api.tx.depositbank.Tx4772Response;
import payment.api.tx.depositbank.Tx4774Response;
import payment.api.tx.depositbank.Tx4775Response;
import payment.api.tx.depositbank.Tx4776Response;
import payment.api.tx.depositbank.Tx4781Response;
import payment.api.tx.depositbank.Tx4782Response;
import payment.api.tx.depositbank.Tx4784Response;
import payment.api.tx.depositbank.Tx4791Response;
import payment.api.tx.depositbank.Tx4792Response;
import payment.api.tx.depositbank.Tx4801Response;
import payment.api.tx.depositbank.Tx4802Response;
import payment.api.tx.depositbank.Tx4803Response;
import payment.api.tx.depositbank.Tx4804Response;
import payment.api.tx.depositbank.Tx4811Response;
import payment.api.tx.depositbank.Tx4812Response;
import payment.api.tx.depositbank.Tx4813Response;
import payment.api.tx.depositbank.Tx4814Response;
import payment.api.tx.depositbank.Tx4816Response;
import payment.api.tx.file.Tx2901Response;
import payment.api.tx.file.Tx2903Response;
import payment.api.tx.file.Tx2904Response;
import payment.api.tx.file.Tx2911Response;
import payment.api.tx.fund.Tx2330Response;
import payment.api.tx.fund.Tx2331Response;
import payment.api.tx.fund.Tx6101Response;
import payment.api.tx.fund.Tx6102Response;
import payment.api.tx.fund.Tx6111Response;
import payment.api.tx.fund.Tx6112Response;
import payment.api.tx.fund.Tx6116Response;
import payment.api.tx.fund.Tx6121Response;
import payment.api.tx.fund.Tx6122Response;
import payment.api.tx.fund.Tx6124Response;
import payment.api.tx.fund.Tx6125Response;
import payment.api.tx.fund.Tx6126Response;
import payment.api.tx.fund.Tx6127Response;
import payment.api.tx.fund.Tx6131Response;
import payment.api.tx.fund.Tx6132Response;
import payment.api.tx.fund.Tx6133Response;
import payment.api.tx.fund.Tx6134Response;
import payment.api.tx.fund.Tx6135Response;
import payment.api.tx.gatheringaccredit.Tx2703Response;
import payment.api.tx.gatheringbatch.Tx1610Response;
import payment.api.tx.gatheringbatch.Tx1620Response;
import payment.api.tx.gatheringbatch.Tx1630Response;
import payment.api.tx.gatheringbatch.Tx1650Response;
import payment.api.tx.guarantee.Tx1431Response;
import payment.api.tx.guarantee.Tx1432Response;
import payment.api.tx.marketorder.Tx1301Response;
import payment.api.tx.marketorder.Tx1302Response;
import payment.api.tx.marketorder.Tx1303Response;
import payment.api.tx.marketorder.Tx1320Response;
import payment.api.tx.marketorder.Tx1321Response;
import payment.api.tx.marketorder.Tx1330Response;
import payment.api.tx.marketorder.Tx1333Response;
import payment.api.tx.marketorder.Tx1342Response;
import payment.api.tx.marketorder.Tx1344Response;
import payment.api.tx.marketorder.Tx134xResponse;
import payment.api.tx.marketorder.Tx1350Response;
import payment.api.tx.marketorder.Tx1352Response;
import payment.api.tx.marketorder.Tx1354Response;
import payment.api.tx.marketorder.Tx1355Response;
import payment.api.tx.marketorder.Tx1361Response;
import payment.api.tx.marketorder.Tx1362Response;
import payment.api.tx.marketorder.Tx1365Response;
import payment.api.tx.marketorder.Tx1366Response;
import payment.api.tx.marketorder.Tx1367Response;
import payment.api.tx.marketorder.Tx1371Response;
import payment.api.tx.marketorder.Tx1372Response;
import payment.api.tx.marketorder.Tx1373Response;
import payment.api.tx.marketorder.Tx1374Response;
import payment.api.tx.marketorder.Tx1375Response;
import payment.api.tx.marketorder.Tx1376Response;
import payment.api.tx.marketorder.Tx1380Response;
import payment.api.tx.marketorder.Tx1382Response;
import payment.api.tx.marketorder.Tx1391Response;
import payment.api.tx.marketorder.Tx1392Response;
import payment.api.tx.marketorder.Tx1395Response;
import payment.api.tx.marketorder.Tx1397Response;
import payment.api.tx.marketorder.Tx1401Response;
import payment.api.tx.marketorder.Tx1402Response;
import payment.api.tx.marketorder.Tx1403Response;
import payment.api.tx.marketorder.Tx1404Response;
import payment.api.tx.marketorder.Tx1405Response;
import payment.api.tx.marketorder.Tx1406Response;
import payment.api.tx.marketorder.Tx1410Response;
import payment.api.tx.marketorder.Tx1411Response;
import payment.api.tx.marketorder.Tx1461Response;
import payment.api.tx.marketorder.Tx1463Response;
import payment.api.tx.matchbatch.Tx1910Response;
import payment.api.tx.matchbatch.Tx1920Response;
import payment.api.tx.matchbatch.Tx1930Response;
import payment.api.tx.matchbatch.Tx1950Response;
import payment.api.tx.merchantorder.Tx1120Response;
import payment.api.tx.merchantorder.Tx1121Response;
import payment.api.tx.merchantorder.Tx1131Response;
import payment.api.tx.merchantorder.Tx1132Response;
import payment.api.tx.merchantorder.Tx1133Response;
import payment.api.tx.o2o.Tx2210Response;
import payment.api.tx.o2o.Tx2220Response;
import payment.api.tx.o2o.Tx2231Response;
import payment.api.tx.o2o.Tx2232Response;
import payment.api.tx.p2p.Tx3116Response;
import payment.api.tx.p2p.Tx3131Response;
import payment.api.tx.p2p.Tx3136Response;
import payment.api.tx.p2p.Tx3137Response;
import payment.api.tx.p2p.Tx3138Response;
import payment.api.tx.p2p.Tx3141Response;
import payment.api.tx.p2p.Tx3146Response;
import payment.api.tx.p2p.Tx3151Response;
import payment.api.tx.p2p.Tx3156Response;
import payment.api.tx.p2p.Tx3161Response;
import payment.api.tx.p2p.Tx3162Response;
import payment.api.tx.p2p.Tx3216Response;
import payment.api.tx.p2p.Tx3231Response;
import payment.api.tx.p2p.Tx3236Response;
import payment.api.tx.p2p.Tx3237Response;
import payment.api.tx.p2p.Tx3238Response;
import payment.api.tx.p2p.Tx3241Response;
import payment.api.tx.p2p.Tx3246Response;
import payment.api.tx.p2p.Tx3251Response;
import payment.api.tx.p2p.Tx3256Response;
import payment.api.tx.p2p.Tx3261Response;
import payment.api.tx.p2p.Tx3262Response;
import payment.api.tx.p2p.Tx3291Response;
import payment.api.tx.p2p.Tx3292Response;
import payment.api.tx.p2p.Tx3310Response;
import payment.api.tx.p2p.Tx3312Response;
import payment.api.tx.p2p.Tx3315Response;
import payment.api.tx.p2poptimize.Tx3601Response;
import payment.api.tx.p2poptimize.Tx3602Response;
import payment.api.tx.p2poptimize.Tx3612Response;
import payment.api.tx.p2poptimize.Tx3618Response;
import payment.api.tx.p2poptimize.Tx3631Response;
import payment.api.tx.p2poptimize.Tx3632Response;
import payment.api.tx.p2poptimize.Tx3641Response;
import payment.api.tx.p2poptimize.Tx3642Response;
import payment.api.tx.p2poptimize.Tx3643Response;
import payment.api.tx.p2poptimize.Tx3644Response;
import payment.api.tx.paymentaccount.Tx4202Response;
import payment.api.tx.paymentaccount.Tx4206Response;
import payment.api.tx.paymentaccount.Tx4211Response;
import payment.api.tx.paymentaccount.Tx4212Response;
import payment.api.tx.paymentaccount.Tx4221Response;
import payment.api.tx.paymentaccount.Tx4232Response;
import payment.api.tx.paymentaccount.Tx4236Response;
import payment.api.tx.paymentaccount.Tx4237Response;
import payment.api.tx.paymentaccount.Tx4238Response;
import payment.api.tx.paymentaccount.Tx4239Response;
import payment.api.tx.paymentaccount.Tx4244Response;
import payment.api.tx.paymentaccount.Tx4252Response;
import payment.api.tx.paymentaccount.Tx4256Response;
import payment.api.tx.paymentaccount.Tx4264Response;
import payment.api.tx.paymentaccount.Tx4273Response;
import payment.api.tx.paymentaccount.Tx4276Response;
import payment.api.tx.paymentaccount.Tx4280Response;
import payment.api.tx.paymentaccount.Tx4321Response;
import payment.api.tx.paymentaccount.Tx4328Response;
import payment.api.tx.paymentaccount.Tx4333Response;
import payment.api.tx.paymentaccount.Tx4336Response;
import payment.api.tx.paymentbatch.Tx1510Response;
import payment.api.tx.paymentbatch.Tx1520Response;
import payment.api.tx.paymentbatch.Tx1550Response;
import payment.api.tx.paymentbinding.Tx2501Response;
import payment.api.tx.paymentbinding.Tx2502Response;
import payment.api.tx.paymentbinding.Tx2503Response;
import payment.api.tx.paymentbinding.Tx2511Response;
import payment.api.tx.paymentbinding.Tx2512Response;
import payment.api.tx.paymentbinding.Tx2521Response;
import payment.api.tx.paymentbinding.Tx2522Response;
import payment.api.tx.paymentbinding.Tx2531Response;
import payment.api.tx.paymentbinding.Tx2532Response;
import payment.api.tx.paymentbinding.Tx2541Response;
import payment.api.tx.paymentbinding.Tx2542Response;
import payment.api.tx.paymentbinding.Tx2553Response;
import payment.api.tx.paymentbinding.Tx2561Response;
import payment.api.tx.paymentbinding.Tx2562Response;
import payment.api.tx.paymentbinding.Tx2563Response;
import payment.api.tx.paymentbinding.Tx2571Response;
import payment.api.tx.paymentbinding.Tx2572Response;
import payment.api.tx.paymentbinding.Tx2573Response;
import payment.api.tx.paymentbinding.Tx2574Response;
import payment.api.tx.paymentbinding.Tx2575Response;
import payment.api.tx.pos.Tx6011Response;
import payment.api.tx.pos.Tx6012Response;
import payment.api.tx.pos.Tx6013Response;
import payment.api.tx.pos.Tx6021Response;
import payment.api.tx.pos.Tx6022Response;
import payment.api.tx.pos.Tx6031Response;
import payment.api.tx.pos.Tx6032Response;
import payment.api.tx.pos.Tx6041Response;
import payment.api.tx.realgathering.Tx2011Response;
import payment.api.tx.realgathering.Tx2020Response;
import payment.api.tx.realgathering.Tx2031Response;
import payment.api.tx.realgathering.Tx2040Response;
import payment.api.tx.scanpay.Tx2811Response;
import payment.api.tx.scanpay.Tx2812Response;
import payment.api.tx.scanpay.Tx2813Response;
import payment.api.tx.scanpay.Tx2814Response;
import payment.api.tx.scanpay.Tx2820Response;
import payment.api.tx.scanpay.Tx2831Response;
import payment.api.tx.scanpay.Tx2832Response;
import payment.api.tx.scanpay.Tx2841Response;
import payment.api.tx.statement.Tx1810Response;
import payment.api.tx.statement.Tx1811Response;
import payment.api.tx.statement.Tx1840Response;
import payment.api.tx.steppayment.Tx2110Response;
import payment.api.tx.steppayment.Tx2120Response;
import payment.api.tx.steppayment.Tx2131Response;
import payment.api.tx.steppayment.Tx2132Response;
import payment.api.tx.unionpay.Tx7620Response;
import payment.api.tx.unionpay.Tx7621Response;
import payment.api.tx.whitelist.Tx2601Response;
import payment.api.tx.whitelist.Tx2603Response;
import payment.api.tx.whitelist.Tx2605Response;
import payment.api.tx.whitelist.Tx2611Response;
import payment.api.vo.GatheringItem;
import payment.api.vo.Item;
import payment.api.vo.MarketOrderAuthPayment;
import payment.api.vo.MarketOrderBookPayment;
import payment.api.vo.MarketOrderGathering;
import payment.api.vo.MarketOrderGatheringSMS;
import payment.api.vo.MarketOrderPOSPayment;
import payment.api.vo.MarketOrderQuickPayment;
import payment.api.vo.MarketOrderSettlement;
import payment.api.vo.MatchBatchItem;
import payment.api.vo.OrderPayment;
import payment.api.vo.PaymentBankAccount;
import payment.api.vo.PaymentItem;
import payment.api.vo.SupervisionBankTx;
import payment.api.vo.Tx;

/**
 * @author Administrator
 *
 */
@Service(value="SendMessage")
public class SendMessage {
	 private static final long serialVersionUID = -9113676340998312853L;

	 private static final Logger logger = Logger.getLogger("system");


	    
	public String SendMessage(HttpServletRequest request, HttpServletResponse response){
		       String data=null;
		  try {
	            // 获得参数message和signature
	            String message = request.getAttribute("message").toString();
	            String signature = request.getAttribute("signature").toString();
	            String txCode = request.getAttribute("txCode").toString();
	            String txName = request.getAttribute("txName").toString();
	            String flag = request.getAttribute("Flag").toString();
	            // 与支付平台进行通讯
	            TxMessenger txMessenger = new TxMessenger();
	            String[] respMsg = null;
	            
	            // Flag=10:cmb, 20:paymentAccount
	            if ("10".equals(flag)) {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature, CMBEnvironment.cmbtxURL);// 0:message;
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit,
	                // CMBEnvironment.cmbtxURL);// 0:message;
	            } else if ("20".equals(flag)) {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature, PaymentUserEnvironment.paymentusertxURL);
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit,
	                // PaymentUserEnvironment.paymentusertxURL);// 0:message;
	            } else if ("30".equals(flag)) {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature, CrossBorderEnvironment.crossBorderTxURL);
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit,
	                // PaymentUserEnvironment.paymentusertxURL);// 0:message;
	            } else if ("40".equals(flag)) {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature, Gateway4DepositBankEnvironment.gateway4depositbanktxURL);
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit,
	                // PaymentUserEnvironment.paymentusertxURL);// 0:message;
	            } else if ("50".equals(flag)) {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature, Gateway4FileEnvironment.GATEWAY4FILE_URL);
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit,
	                // PaymentUserEnvironment.paymentusertxURL);// 0:message;
	            } else if ("60".equals(flag)) {
	                respMsg = txMessenger.send(message, signature, Gateway4ConsumerEnvironment.GATEWAY4ConsumerTxURL);
	            } else if ("70".equals(flag)) {
	                respMsg = txMessenger.send(message, signature, Gateway4POSEnvironment.POS_URL);//POS支付
	            }else {
	                // HTTP默认连接超时时间是25s，读超时时间是25s
	                // HTTPS默认连接超时时间是50s，读超时时间是50s
	                respMsg = txMessenger.send(message, signature);// 0:message;
	                // 设置连接超时时间和读超时时间
	                // int connectTimeoutLimit = 50000; 设置连接超时时间（单位ms）
	                // int readTimeoutLimit = 50000; 设置读超时时间（单位ms）
	                // respMsg = txMessenger.send(message, signature,
	                // connectTimeoutLimit, readTimeoutLimit);// 0:message;
	            }
	            // 1:signature
	            String plainText = new String(cpcn.institution.tools.util.Base64.decode(respMsg[0]), "UTF-8");

	            logger.debug("[message]=[" + respMsg[0] + "]");
	            logger.debug("[signature]=[" + respMsg[1] + "]");
	            logger.debug("[plainText]=[" + plainText + "]");

	            // 将结果保存在request中，以备在Response.jsp页面显示
	            // //商户交易
	            if ("1120".equals(txCode)) {
	                Tx1120Response tx1120Response = new Tx1120Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1120Response.getResponsePlainText());
	                if ("2000".equals(tx1120Response.getCode())) {
	                    logger.info("[Message]=[" + tx1120Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1121".equals(txCode)) {
	                Tx1121Response tx1121Response = new Tx1121Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1121Response.getResponsePlainText());
	                if ("2000".equals(tx1121Response.getCode())) {
	                    logger.info("[Message]=[" + tx1121Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("7620".equals(txCode)) {
	                Tx7620Response tx7620Response = new Tx7620Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx7620Response.getResponsePlainText());
	                if ("2000".equals(tx7620Response.getCode())) {
	                    logger.info("[Message]=[" + tx7620Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("7621".equals(txCode)) {
	                Tx7621Response tx7621Response = new Tx7621Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx7621Response.getResponsePlainText());
	                if ("2000".equals(tx7621Response.getCode())) {
	                    logger.info("[Message]=[" + tx7621Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1131".equals(txCode)) {
	                Tx1131Response tx1131Response = new Tx1131Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1131Response.getResponsePlainText());
	                if ("2000".equals(tx1131Response.getCode())) {
	                    logger.info("[Message]=[" + tx1131Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1132".equals(txCode)) {
	                Tx1132Response tx1132Response = new Tx1132Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1132Response.getResponsePlainText());
	                if ("2000".equals(tx1132Response.getCode())) {
	                    logger.info("[Message]=[" + tx1132Response.getMessage() + "]");
	                    if (tx1132Response.getAccountType() == 20) {
	                        logger.info("[PaymentAccountName]=[" + tx1132Response.getPaymentAccountName() + "]");
	                        logger.info("[PaymentAccountNumber]=[" + tx1132Response.getPaymentAccountNumber() + "]");
	                    } else {
	                        logger.info("[BankID]=[" + tx1132Response.getBankAccount().getBankID() + "]");
	                        logger.info("[AccountName]=[" + tx1132Response.getBankAccount().getAccountName() + "]");
	                        logger.info("[AccountNumber]=[" + tx1132Response.getBankAccount().getAccountNumber() + "]");
	                        logger.info("[BranchName]=[" + tx1132Response.getBankAccount().getBranchName() + "]");
	                        logger.info("[Province]=[" + tx1132Response.getBankAccount().getProvince() + "]");
	                        logger.info("[City]=[" + tx1132Response.getBankAccount().getCity() + "]");
	                    }
	                    logger.info("[Status]=[" + tx1132Response.getStatus() + "]");
	                    // 处理业务
	                }
	            } else if ("1133".equals(txCode)) {
	                Tx1133Response tx1133Response = new Tx1133Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1133Response.getResponsePlainText());
	                if ("2000".equals(tx1133Response.getCode())) {
	                    logger.info("[Message]=[" + tx1133Response.getMessage() + "]");
	                    // 处理业务
	                }
	            }
	            // //交易市场交易
	            else if ("1301".equals(txCode)) { // 市场订单绑定并支付后台模式（无验证短信）
	                Tx1301Response txResponse = new Tx1301Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("1302".equals(txCode)) { // 市场订单绑定并支付后台模式（发送验证短信）
	                Tx1302Response txResponse = new Tx1302Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("1303".equals(txCode)) { // 市场订单绑定并支付后台模式（验证并支付）
	                Tx1303Response txResponse = new Tx1303Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("1320".equals(txCode)) {
	                Tx1320Response tx1320Response = new Tx1320Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1320Response.getResponsePlainText());
	                if ("2000".equals(tx1320Response.getCode())) {
	                    logger.info("[Message]=[" + tx1320Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1321".equals(txCode)) {
	                Tx1321Response tx1321Response = new Tx1321Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1321Response.getResponsePlainText());
	                if ("2000".equals(tx1321Response.getCode())) {
	                    logger.info("[Message]=[" + tx1321Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1330".equals(txCode)) {
	                Tx1330Response tx1330Response = new Tx1330Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1330Response.getResponsePlainText());
	                if ("2000".equals(tx1330Response.getCode())) {
	                    logger.info("[Message]=[" + tx1330Response.getMessage() + "]");
	                    List<OrderPayment> orderPaymentList = tx1330Response.getOrderPaymentList();

	                    for (OrderPayment orderPayment : orderPaymentList) {
	                        logger.info("[TxType]=[" + orderPayment.getTxType() + "]");
	                        logger.info("[PaymentNo]=[" + orderPayment.getPaymentNo() + "]");
	                        logger.info("[Amount]=[" + orderPayment.getAmount() + "]");
	                        logger.info("[Remark]=[" + orderPayment.getRemark() + "]");
	                        logger.info("[Status]=[" + orderPayment.getStatus() + "]");
	                    }

	                    List<MarketOrderSettlement> marketOrderSettlementList = tx1330Response.getOrderSettlementList();
	                    if (marketOrderSettlementList != null) {
	                        for (MarketOrderSettlement marketOrderSettlement : marketOrderSettlementList) {
	                            logger.info("[TxType]=[" + marketOrderSettlement.getTxType() + "]");
	                            logger.info("[SerialNumber]=[" + marketOrderSettlement.getSerialNumber() + "]");
	                            logger.info("[Amount]=[" + marketOrderSettlement.getAmount() + "]");
	                            logger.info("[Remark]=[" + marketOrderSettlement.getRemark() + "]");
	                            logger.info("[Status]=[" + marketOrderSettlement.getStatus() + "]");
	                        }
	                    }
	                    List<MarketOrderGathering> marketOrderGatheringList = tx1330Response.getOrderGatheringList();
	                    if (marketOrderGatheringList != null) {
	                        for (MarketOrderGathering marketOrderGathering : marketOrderGatheringList) {
	                            logger.info("[TxType]=[" + marketOrderGathering.getTxType() + "]");
	                            logger.info("[TxSN]=[" + marketOrderGathering.getTxSN() + "]");
	                            logger.info("[Amount]=[" + marketOrderGathering.getAmount() + "]");
	                            logger.info("[Remark]=[" + marketOrderGathering.getRemark() + "]");
	                            logger.info("[Status]=[" + marketOrderGathering.getStatus() + "]");
	                        }
	                    }
	                    List<MarketOrderQuickPayment> marketOrderQuickPaymentList = tx1330Response.getOrderQuickPaymentList();
	                    if (marketOrderQuickPaymentList != null) {
	                        for (MarketOrderQuickPayment marketOrderQuickPayment : marketOrderQuickPaymentList) {
	                            logger.info("[TxType]=[" + marketOrderQuickPayment.getTxType() + "]");
	                            logger.info("[TxSN]=[" + marketOrderQuickPayment.getTxSN() + "]");
	                            logger.info("[Amount]=[" + marketOrderQuickPayment.getAmount() + "]");
	                            logger.info("[Remark]=[" + marketOrderQuickPayment.getRemark() + "]");
	                            logger.info("[Status]=[" + marketOrderQuickPayment.getStatus() + "]");
	                        }
	                    }
	                    List<MarketOrderPOSPayment> marketOrderPOSPaymentList = tx1330Response.getOrderPOSPaymentList();
	                    if (marketOrderPOSPaymentList != null) {
	                        for (MarketOrderPOSPayment marketOrderPOSPayment : marketOrderPOSPaymentList) {
	                            logger.info("[TxType]=[" + marketOrderPOSPayment.getTxType() + "]");
	                            logger.info("[TxSN]=[" + marketOrderPOSPayment.getTxSN() + "]");
	                            logger.info("[PhoneNumber]=[" + marketOrderPOSPayment.getPhoneNumber() + "]");
	                            logger.info("[BillNo]=[" + marketOrderPOSPayment.getBillNo() + "]");
	                            logger.info("[Amount]=[" + marketOrderPOSPayment.getAmount() + "]");
	                            logger.info("[Status]=[" + marketOrderPOSPayment.getStatus() + "]");
	                            logger.info("[Remark]=[" + marketOrderPOSPayment.getRemark() + "]");
	                        }
	                    }
	                    List<MarketOrderAuthPayment> marketOrderAuthPaymentList = tx1330Response.getOrderAuthPaymentList();
	                    if (marketOrderAuthPaymentList != null) {
	                        for (MarketOrderAuthPayment marketOrderAuthPayment : marketOrderAuthPaymentList) {
	                            logger.info("[TxType]=[" + marketOrderAuthPayment.getTxType() + "]");
	                            logger.info("[PaymentNo]=[" + marketOrderAuthPayment.getPaymentNo() + "]");
	                            logger.info("[Amount]=[" + marketOrderAuthPayment.getAmount() + "]");
	                            logger.info("[Status]=[" + marketOrderAuthPayment.getStatus() + "]");
	                            logger.info("[MerchantID]=[" + marketOrderAuthPayment.getMerchantID() + "]");
	                            logger.info("[MerchantName]=[" + marketOrderAuthPayment.getMerchantName() + "]");
	                            logger.info("[MCC]=[" + marketOrderAuthPayment.getMcc() + "]");
	                            logger.info("[Remark]=[" + marketOrderAuthPayment.getRemark() + "]");
	                        }
	                    }

	                    List<MarketOrderBookPayment> marketOrderBookPaymentList = tx1330Response.getOrderBookPaymentList();
	                    if (marketOrderBookPaymentList != null) {
	                        for (MarketOrderBookPayment marketOrderBookPayment : marketOrderBookPaymentList) {
	                            logger.info("[TxType]=[" + marketOrderBookPayment.getTxType() + "]");
	                            logger.info("[PaymentNo]=[" + marketOrderBookPayment.getPaymentNo() + "]");
	                            logger.info("[Amount]=[" + marketOrderBookPayment.getAmount() + "]");
	                            logger.info("[Status]=[" + marketOrderBookPayment.getStatus() + "]");
	                            logger.info("[Remark]=[" + marketOrderBookPayment.getRemark() + "]");
	                        }
	                    }
	                    List<MarketOrderGatheringSMS> marketOrderGatheringSMSList = tx1330Response.getOrderGatheringSMSList();
	                    if (marketOrderGatheringSMSList != null) {
	                        for (MarketOrderGatheringSMS marketOrderGatheringSMS : marketOrderGatheringSMSList) {
	                            logger.info("[TxType]=[" + marketOrderGatheringSMS.getTxType() + "]");
	                            logger.info("[TxSN]=[" + marketOrderGatheringSMS.getTxSN() + "]");
	                            logger.info("[Amount]=[" + marketOrderGatheringSMS.getAmount() + "]");
	                            logger.info("[Remark]=[" + marketOrderGatheringSMS.getRemark() + "]");
	                            logger.info("[Status]=[" + marketOrderGatheringSMS.getStatus() + "]");
	                            logger.info("[SMSFlag]=[" + marketOrderGatheringSMS.getSMSFlag() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1333".equals(txCode)) {
	                Tx1333Response tx1333Response = new Tx1333Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1333Response.getResponsePlainText());
	                if ("2000".equals(tx1333Response.getCode())) {
	                    logger.info("[Message]=[" + tx1333Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1341".equals(txCode) || "1343".equals(txCode)) {
	                Tx134xResponse tx134xResponse = new Tx134xResponse(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx134xResponse.getResponsePlainText());
	                if ("2000".equals(tx134xResponse.getCode())) {
	                    logger.info("[Message]=[" + tx134xResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1342".equals(txCode)) {
	                Tx1342Response tx1342Response = new Tx1342Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1342Response.getResponsePlainText());
	                if ("2000".equals(tx1342Response.getCode())) {
	                    logger.info("[Message]=[" + tx1342Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1344".equals(txCode)) {
	                Tx1344Response tx1344Response = new Tx1344Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1344Response.getResponsePlainText());
	                if ("2000".equals(tx1344Response.getCode())) {
	                    logger.info("[Message]=[" + tx1344Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1350".equals(txCode)) {
	                Tx1350Response tx1350Response = new Tx1350Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1350Response.getResponsePlainText());
	                if ("2000".equals(tx1350Response.getCode())) {
	                    logger.info("[Message]=[" + tx1350Response.getMessage() + "]");
	                    if (tx1350Response.getAccountType() == 20) {
	                        logger.info("[PaymentAccountName]=[" + tx1350Response.getPaymentAccountName() + "]");
	                        logger.info("[PaymentAccountNumber]=[" + tx1350Response.getPaymentAccountNumber() + "]");
	                    } else {
	                        logger.info("[BankID]=[" + tx1350Response.getBankAccount().getBankID() + "]");
	                        logger.info("[AccountName]=[" + tx1350Response.getBankAccount().getAccountName() + "]");
	                        logger.info("[AccountNumber]=[" + tx1350Response.getBankAccount().getAccountNumber() + "]");
	                        logger.info("[BranchName]=[" + tx1350Response.getBankAccount().getBranchName() + "]");
	                        logger.info("[Province]=[" + tx1350Response.getBankAccount().getProvince() + "]");
	                        logger.info("[City]=[" + tx1350Response.getBankAccount().getCity() + "]");
	                    }

	                    logger.info("[Status]=[" + tx1350Response.getStatus() + "]");
	                    // 处理业务
	                }
	            } else if ("1352".equals(txCode)) {
	                Tx1352Response tx1352Response = new Tx1352Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1352Response.getResponsePlainText());
	                if ("2000".equals(tx1352Response.getCode())) {
	                    logger.info("[Message]=[" + tx1352Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1354".equals(txCode)) {
	                Tx1354Response tx1354Response = new Tx1354Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1354Response.getResponsePlainText());
	            } else if ("1355".equals(txCode)) {
	                Tx1355Response tx1355Response = new Tx1355Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1355Response.getResponsePlainText());
	                if ("2000".equals(tx1355Response.getCode())) {
	                    logger.info("[Message]=[" + tx1355Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1361".equals(txCode)) {
	                Tx1361Response txResponse = new Tx1361Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1362".equals(txCode)) {
	                Tx1362Response tx1362Response = new Tx1362Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1362Response.getResponsePlainText());
	                if ("2000".equals(tx1362Response.getCode())) {
	                    logger.info("[Message]=[" + tx1362Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1365".equals(txCode)) {
	                Tx1365Response tx1365Response = new Tx1365Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1365Response.getResponsePlainText());
	                if ("2000".equals(tx1365Response.getCode())) {
	                    logger.info("[Message]=[" + tx1365Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1366".equals(txCode)) {
	                Tx1366Response tx1366Response = new Tx1366Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1366Response.getResponsePlainText());
	                if ("2000".equals(tx1366Response.getCode())) {
	                    logger.info("[Message]=[" + tx1366Response.getMessage() + "]");
	                    if (tx1366Response.getGatheringItemList() != null) {
	                        int size = tx1366Response.getGatheringItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            Item item = tx1366Response.getGatheringItemList().get(i);
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[BankTxTime]=[" + item.getBankTxTime() + "]");
	                            logger.info("[ResponseCode]=[" + item.getResponseCode() + "]");
	                            logger.info("[ResponseMessage]=[" + item.getResponseMessage() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1367".equals(txCode)) {
	                Tx1367Response tx1367Response = new Tx1367Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1367Response.getResponsePlainText());
	                if ("2000".equals(tx1367Response.getCode())) {
	                    logger.info("[Message]=[" + tx1367Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1371".equals(txCode)) {
	                Tx1371Response txResponse = new Tx1371Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1372".equals(txCode)) {
	                Tx1372Response txResponse = new Tx1372Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1373".equals(txCode)) {
	                Tx1373Response txResponse = new Tx1373Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1374".equals(txCode)) {
	                Tx1374Response txResponse = new Tx1374Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1375".equals(txCode)) {
	                Tx1375Response txResponse = new Tx1375Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                	data=txResponse.getCode();
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1376".equals(txCode)) {
	                Tx1376Response txResponse = new Tx1376Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                	data=txResponse.getCode();
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1401".equals(txCode)) {
	                Tx1401Response txResponse = new Tx1401Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                request.setAttribute("imageUrl", txResponse.getImageUrl());
	                request.setAttribute("status", txResponse.getStatus());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1402".equals(txCode)) {
	                Tx1402Response txResponse = new Tx1402Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1403".equals(txCode)) {
	                Tx1403Response txResponse = new Tx1403Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                request.setAttribute("authCode", txResponse.getAuthCode());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1404".equals(txCode)) {
	                Tx1404Response txResponse = new Tx1404Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1405".equals(txCode)) {
	                Tx1405Response txResponse = new Tx1405Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1406".equals(txCode)) {
	                Tx1406Response txResponse = new Tx1406Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1410".equals(txCode)) {
	                Tx1410Response txResponse = new Tx1410Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1411".equals(txCode)) {
	                Tx1411Response txResponse = new Tx1411Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                request.setAttribute("status", txResponse.getStatus());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1461".equals(txCode)) {
	                Tx1461Response txResponse = new Tx1461Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1463".equals(txCode)) {
	                Tx1463Response txResponse = new Tx1463Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1380".equals(txCode)) {
	                Tx1380Response tx1380Response = new Tx1380Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1380Response.getResponsePlainText());
	                if ("2000".equals(tx1380Response.getCode())) {
	                    logger.info("[Message]=[" + tx1380Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1382".equals(txCode)) {
	                Tx1382Response tx1382Response = new Tx1382Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1382Response.getResponsePlainText());
	                if ("2000".equals(tx1382Response.getCode())) {
	                    logger.info("[Message]=[" + tx1382Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1391".equals(txCode)) {
	                Tx1391Response tx1391Response = new Tx1391Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1391Response.getResponsePlainText());
	                if ("2000".equals(tx1391Response.getCode())) {
	                    logger.info("[Message]=[" + tx1391Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1392".equals(txCode)) {
	                Tx1392Response tx1392Response = new Tx1392Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1392Response.getResponsePlainText());
	                if ("2000".equals(tx1392Response.getCode())) {
	                    logger.info("[Message]=[" + tx1392Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1395".equals(txCode)) {
	                Tx1395Response tx1395Response = new Tx1395Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1395Response.getResponsePlainText());
	                if ("2000".equals(tx1395Response.getCode())) {
	                    logger.info("[Message]=[" + tx1395Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1396".equals(txCode)) {
	                Tx1391Response tx1396Response = new Tx1391Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1396Response.getResponsePlainText());
	                if ("2000".equals(tx1396Response.getCode())) {
	                    logger.info("[Message]=[" + tx1396Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1397".equals(txCode)) {
	                Tx1397Response tx1397Response = new Tx1397Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1397Response.getResponsePlainText());
	                if ("2000".equals(tx1397Response.getCode())) {
	                    logger.info("[Message]=[" + tx1397Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1431".equals(txCode)) {
	                Tx1431Response tx1431Response = new Tx1431Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1431Response.getResponsePlainText());
	                if ("2000".equals(tx1431Response.getCode())) {
	                    logger.info("[Message]=[" + tx1431Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1432".equals(txCode)) {
	                Tx1432Response tx1432Response = new Tx1432Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1432Response.getResponsePlainText());
	                if ("2000".equals(tx1432Response.getCode())) {
	                    logger.info("[Message]=[" + tx1432Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1510".equals(txCode)) {
	                Tx1510Response tx1510Response = new Tx1510Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1510Response.getResponsePlainText());
	                if ("2000".equals(tx1510Response.getCode())) {
	                    logger.info("[Message]=[" + tx1510Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1520".equals(txCode)) {
	                Tx1520Response tx1520Response = new Tx1520Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1520Response.getResponsePlainText());
	                if ("2000".equals(tx1520Response.getCode())) {
	                    logger.info("[Message]=[" + tx1520Response.getMessage() + "]");
	                    if (tx1520Response.getItemList() != null) {
	                        int size = tx1520Response.getItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            PaymentItem item = tx1520Response.getItemList().get(i);
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[BankTxTime]=[" + item.getBankTxTime() + "]");
	                            logger.info("[ResponseCode]=[" + item.getResponseCode() + "]");
	                            logger.info("[ResponseMessage]=[" + item.getResponseMessage() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1550".equals(txCode)) {
	                Tx1550Response tx1550Response = new Tx1550Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1550Response.getResponsePlainText());
	                if ("2000".equals(tx1550Response.getCode())) {
	                    logger.info("[Message]=[" + tx1550Response.getMessage() + "]");
	                    if (tx1550Response.getItemList() != null) {
	                        int size = tx1550Response.getItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            PaymentItem item = tx1550Response.getItemList().get(i);
	                            logger.info("[BatchNo]=[" + item.getBatchNo() + "]");
	                            logger.info("[TxTime]=[" + item.getTxTime() + "]");
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[BankTxTime]=[" + item.getBankTxTime() + "]");
	                            logger.info("[ResponseCode]=[" + item.getResponseCode() + "]");
	                            logger.info("[ResponseMessage]=[" + item.getResponseMessage() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1610".equals(txCode)) {
	                Tx1610Response tx1610Response = new Tx1610Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1610Response.getResponsePlainText());
	                if ("2000".equals(tx1610Response.getCode())) {
	                    logger.info("[Message]=[" + tx1610Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1620".equals(txCode)) {
	                Tx1620Response tx1620Response = new Tx1620Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1620Response.getResponsePlainText());
	                if ("2000".equals(tx1620Response.getCode())) {
	                    logger.info("[Message]=[" + tx1620Response.getMessage() + "]");
	                    if (tx1620Response.getGatheringItemList() != null) {
	                        int size = tx1620Response.getGatheringItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            GatheringItem item = tx1620Response.getGatheringItemList().get(i);
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[ResponseCode]=[" + item.getResponseCode() + "]");
	                            logger.info("[ResponseMessage]=[" + item.getResponseMessage() + "]");
	                            logger.info("[SettlementFlag]=[" + item.getSettlementFlag() + "]");
	                            logger.info("[BankTxTime]=[" + item.getBankTxTime() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1630".equals(txCode)) {
	                Tx1630Response tx1630Response = new Tx1630Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1630Response.getResponsePlainText());
	                if ("2000".equals(tx1630Response.getCode())) {
	                    logger.info("[Message]=[" + tx1630Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1650".equals(txCode)) {
	                Tx1650Response tx1650Response = new Tx1650Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1650Response.getResponsePlainText());
	                if ("2000".equals(tx1650Response.getCode())) {
	                    logger.info("[Message]=[" + tx1650Response.getMessage() + "]");
	                    if (tx1650Response.getGatheringItemList() != null) {
	                        int size = tx1650Response.getGatheringItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            GatheringItem item = tx1650Response.getGatheringItemList().get(i);
	                            logger.info("[BatchNo]=[" + item.getBatchNo() + "]");
	                            logger.info("[TxTime]=[" + item.getTxTime() + "]");
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[ResponseCode]=[" + item.getResponseCode() + "]");
	                            logger.info("[ResponseMessage]=[" + item.getResponseMessage() + "]");
	                            logger.info("[SettlementFlag]=[" + item.getSettlementFlag() + "]");
	                            logger.info("[BankTxTime]=[" + item.getBankTxTime() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1713".equals(txCode)) {
	                Tx1713Response tx1713Response = new Tx1713Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1713Response.getResponsePlainText());
	                if ("2000".equals(tx1713Response.getCode())) {
	                    logger.info("[Message]=[" + tx1713Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1721".equals(txCode)) {
	                Tx1721Response tx1721Response = new Tx1721Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1721Response.getResponsePlainText());
	                if ("2000".equals(tx1721Response.getCode())) {
	                    logger.info("[Message]=[" + tx1721Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1723".equals(txCode)) {
	                Tx1723Response tx1723Response = new Tx1723Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1723Response.getResponsePlainText());
	                if ("2000".equals(tx1723Response.getCode())) {
	                    logger.info("[Message]=[" + tx1723Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1731".equals(txCode)) {
	                Tx1731Response tx1731Response = new Tx1731Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1731Response.getResponsePlainText());
	                if ("2000".equals(tx1731Response.getCode())) {
	                    logger.info("[Message]=[" + tx1731Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1733".equals(txCode)) {
	                Tx1733Response tx1733Response = new Tx1733Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1733Response.getResponsePlainText());
	                if ("2000".equals(tx1733Response.getCode())) {
	                    logger.info("[Message]=[" + tx1733Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1741".equals(txCode)) {
	                Tx1741Response tx1741Response = new Tx1741Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1741Response.getResponsePlainText());
	                if ("2000".equals(tx1741Response.getCode())) {
	                    logger.info("[Message]=[" + tx1741Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1810".equals(txCode)) {
	                Tx1810Response txResponse = new Tx1810Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    List<Tx> txList = txResponse.getTxList();
	                    if (txList != null) {
	                        int size = txList.size();
	                        for (int i = 0; i < size; i++) {
	                            Tx tx = txList.get(i);
	                            logger.info("[TxType]=[" + tx.getTxType() + "]");
	                            logger.info("[TxSn]=[" + tx.getTxSn() + "]");
	                            logger.info("[TxAmount]=[" + tx.getTxAmount() + "]");
	                            logger.info("[InstitutionAmount]=[" + tx.getInstitutionAmount() + "]");
	                            logger.info("[PaymentAmount]=[" + tx.getPaymentAmount() + "]");
	                            logger.info("[InstitutionFee]=[" + tx.getInstitutionFee() + "]");
	                            logger.info("[Remark]=[" + tx.getRemark() + "]");
	                            logger.info("[SettlementFlag]=[" + tx.getSettlementFlag() + "]");
	                            logger.info("[BankNotificationTime]=[" + tx.getBankNotificationTime() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1811".equals(txCode)) {
	                Tx1811Response txResponse = new Tx1811Response(respMsg[0], respMsg[1]);

	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    List<Tx> txList = txResponse.getTxList();
	                    if (txList != null) {
	                        int size = txList.size();
	                        for (int i = 0; i < size; i++) {
	                            Tx tx = txList.get(i);
	                            logger.info("[TxType]=[" + tx.getTxType() + "]");
	                            logger.info("[TxSn]=[" + tx.getTxSn() + "]");
	                            logger.info("[TxAmount]=[" + tx.getTxAmount() + "]");
	                            logger.info("[InstitutionAmount]=[" + tx.getInstitutionAmount() + "]");
	                            logger.info("[PaymentAmount]=[" + tx.getPaymentAmount() + "]");
	                            logger.info("[InstitutionFee]=[" + tx.getInstitutionFee() + "]");
	                            logger.info("[Remark]=[" + tx.getRemark() + "]");
	                            logger.info("[SettlementFlag]=[" + tx.getSettlementFlag() + "]");
	                            logger.info("[BankNotificationTime]=[" + tx.getBankNotificationTime() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1840".equals(txCode)) {
	                Tx1840Response txResponse = new Tx1840Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    List<SupervisionBankTx> txList = txResponse.getTxList();
	                    if (txList != null) {
	                        int size = txList.size();
	                        for (int i = 0; i < size; i++) {
	                            SupervisionBankTx tx = txList.get(i);
	                            logger.info("[TxType]=[" + tx.getTxType() + "]");
	                            logger.info("[TxSn]=[" + tx.getTxSn() + "]");
	                            logger.info("[TxAmount]=[" + tx.getTxAmount() + "]");
	                            logger.info("[InstitutionAmount]=[" + tx.getInstitutionAmount() + "]");
	                            logger.info("[PaymentAmount]=[" + tx.getPaymentAmount() + "]");
	                            logger.info("[PayerFee]=[" + tx.getPayerFee() + "]");
	                            logger.info("[InstitutionFee]=[" + tx.getInstitutionFee() + "]");
	                            logger.info("[Remark]=[" + tx.getRemark() + "]");
	                            logger.info("[PayerID]=[" + tx.getPayerID() + "]");
	                            logger.info("[PayerName]=[" + tx.getPayerName() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1910".equals(txCode)) {
	                Tx1910Response tx1910Response = new Tx1910Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1910Response.getResponsePlainText());
	                if ("2000".equals(tx1910Response.getCode())) {
	                    logger.info("[Message]=[" + tx1910Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("1920".equals(txCode)) {
	                Tx1920Response tx1920Response = new Tx1920Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1920Response.getResponsePlainText());
	                if ("2000".equals(tx1920Response.getCode())) {
	                    logger.info("[Message]=[" + tx1920Response.getMessage() + "]");
	                    if (tx1920Response.getItemList() != null) {
	                        int size = tx1920Response.getItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            MatchBatchItem item = tx1920Response.getItemList().get(i);
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[GatheringBankID]=[" + item.getGatheringBankAccount().getBankID() + "]");
	                            logger.info("[GatheringAccountType]=[" + item.getGatheringBankAccount().getAccountType() + "]");
	                            logger.info("[GatheringAccountName]=[" + item.getGatheringBankAccount().getAccountName() + "]");
	                            logger.info("[GatheringAccountNumber]=[" + item.getGatheringBankAccount().getAccountNumber() + "]");
	                            logger.info("[GatheringBranchName]=[" + item.getGatheringBankAccount().getBranchName() + "]");
	                            logger.info("[GatheringProvince]=[" + item.getGatheringBankAccount().getProvince() + "]");
	                            logger.info("[GatheringCity]=[" + item.getGatheringBankAccount().getCity() + "]");
	                            logger.info("[PaymentBankID]=[" + item.getPaymentBankAccount().getBankID() + "]");
	                            logger.info("[PaymentAccountType]=[" + item.getPaymentBankAccount().getAccountType() + "]");
	                            logger.info("[PaymentAccountName]=[" + item.getPaymentBankAccount().getAccountName() + "]");
	                            logger.info("[PaymentAccountNumber]=[" + item.getPaymentBankAccount().getAccountNumber() + "]");
	                            logger.info("[PaymentBranchName]=[" + item.getPaymentBankAccount().getBranchName() + "]");
	                            logger.info("[PaymentProvince]=[" + item.getPaymentBankAccount().getProvince() + "]");
	                            logger.info("[PaymentCity]=[" + item.getPaymentBankAccount().getCity() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            // logger.info("[BankTxTime]=[" +
	                            // item.getBankTxTime() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("1930".equals(txCode)) {
	                Tx1930Response tx1930Response = new Tx1930Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1930Response.getResponsePlainText());
	                if ("2000".equals(tx1930Response.getCode())) {
	                    logger.info("[Message]=[" + tx1930Response.getMessage() + "]");
	                }
	                // 处理业务
	            } else if ("1950".equals(txCode)) {
	                Tx1950Response tx1950Response = new Tx1950Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx1950Response.getResponsePlainText());
	                if ("2000".equals(tx1950Response.getCode())) {
	                    logger.info("[Message]=[" + tx1950Response.getMessage() + "]");
	                    if (tx1950Response.getItemList() != null) {
	                        int size = tx1950Response.getItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            MatchBatchItem item = tx1950Response.getItemList().get(i);
	                            logger.info("[BatchNo]=[" + item.getBatchNo() + "]");
	                            logger.info("[TxTime]=[" + item.getTxTime() + "]");
	                            logger.info("[ItemNo]=[" + item.getItemNo() + "]");
	                            logger.info("[Amount]=[" + item.getAmount() + "]");
	                            logger.info("[GatheringBankID]=[" + item.getGatheringBankAccount().getBankID() + "]");
	                            logger.info("[GatheringAccountType]=[" + item.getGatheringBankAccount().getAccountType() + "]");
	                            logger.info("[GatheringAccountName]=[" + item.getGatheringBankAccount().getAccountName() + "]");
	                            logger.info("[GatheringAccountNumber]=[" + item.getGatheringBankAccount().getAccountNumber() + "]");
	                            logger.info("[GatheringBranchName]=[" + item.getGatheringBankAccount().getBranchName() + "]");
	                            logger.info("[GatheringProvince]=[" + item.getGatheringBankAccount().getProvince() + "]");
	                            logger.info("[GatheringCity]=[" + item.getGatheringBankAccount().getCity() + "]");
	                            logger.info("[PaymentBankID]=[" + item.getPaymentBankAccount().getBankID() + "]");
	                            logger.info("[PaymentAccountType]=[" + item.getPaymentBankAccount().getAccountType() + "]");
	                            logger.info("[PaymentAccountName]=[" + item.getPaymentBankAccount().getAccountName() + "]");
	                            logger.info("[PaymentAccountNumber]=[" + item.getPaymentBankAccount().getAccountNumber() + "]");
	                            logger.info("[PaymentBranchName]=[" + item.getPaymentBankAccount().getBranchName() + "]");
	                            logger.info("[PaymentProvince]=[" + item.getPaymentBankAccount().getProvince() + "]");
	                            logger.info("[PaymentCity]=[" + item.getPaymentBankAccount().getCity() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("2011".equals(txCode)) {
	                Tx2011Response txResponse = new Tx2011Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2020".equals(txCode)) {
	                Tx2020Response txResponse = new Tx2020Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2031".equals(txCode)) {
	                Tx2031Response txResponse = new Tx2031Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2040".equals(txCode)) {
	                Tx2040Response txResponse = new Tx2040Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2110".equals(txCode)) {
	                Tx2110Response tx2110Response = new Tx2110Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2110Response.getResponsePlainText());
	                if ("2000".equals(tx2110Response.getCode())) {
	                    logger.info("[Message]=[" + tx2110Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2120".equals(txCode)) {
	                Tx2120Response tx2120Response = new Tx2120Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2120Response.getResponsePlainText());
	                if ("2000".equals(tx2120Response.getCode())) {
	                    logger.info("[Message]=[" + tx2120Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2131".equals(txCode)) {
	                Tx2131Response tx2131Response = new Tx2131Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2131Response.getResponsePlainText());
	                if ("2000".equals(tx2131Response.getCode())) {
	                    logger.info("[Message]=[" + tx2131Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2132".equals(txCode)) {
	                Tx2132Response tx2132Response = new Tx2132Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2132Response.getResponsePlainText());
	                if ("2000".equals(tx2132Response.getCode())) {
	                    logger.info("[Message]=[" + tx2132Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2210".equals(txCode)) {// O2O订单支付预生成
	                Tx2210Response tx2210Response = new Tx2210Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2210Response.getResponsePlainText());
	                if ("2000".equals(tx2210Response.getCode())) {
	                    logger.info("[Message]=[" + tx2210Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2220".equals(txCode)) {// O2O订单支付查询
	                Tx2220Response tx2220Response = new Tx2220Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2220Response.getResponsePlainText());
	                if ("2000".equals(tx2220Response.getCode())) {
	                    logger.info("[Message]=[" + tx2220Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2231".equals(txCode)) {// O2O订单支付退款
	                Tx2231Response tx2231Response = new Tx2231Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2231Response.getResponsePlainText());
	                if ("2000".equals(tx2231Response.getCode())) {
	                    logger.info("[Message]=[" + tx2231Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2232".equals(txCode)) {// O2O订单支付退款查询
	                Tx2232Response tx2232Response = new Tx2232Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2232Response.getResponsePlainText());
	                if ("2000".equals(tx2232Response.getCode())) {
	                    logger.info("[Message]=[" + tx2232Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2310".equals(txCode)) {// 账户验证
	                Tx2310Response tx2310Response = new Tx2310Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2310Response.getResponsePlainText());
	                if ("2000".equals(tx2310Response.getCode())) {
	                    logger.info("[Message]=[" + tx2310Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2340".equals(txCode)) {// 账户验证
	                Tx2340Response tx2340Response = new Tx2340Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2340Response.getResponsePlainText());
	                if ("2000".equals(tx2340Response.getCode())) {
	                    logger.info("[Message]=[" + tx2340Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2341".equals(txCode)) {// 账户验证
	                Tx2341Response tx2341Response = new Tx2341Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2341Response.getResponsePlainText());
	                if ("2000".equals(tx2341Response.getCode())) {
	                    logger.info("[Message]=[" + tx2341Response.getMessage() + "]");
	                    // 处理业务
	                }

	            } else if ("2342".equals(txCode)) {
	                Tx2342Response tx2342Response = new Tx2342Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2342Response.getResponsePlainText());
	                if ("2000".equals(tx2342Response.getCode())) {
	                    logger.info("[Message]=[" + tx2342Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2351".equals(txCode)) {
	                Tx2342Response tx2342Response = new Tx2342Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2342Response.getResponsePlainText());
	                if ("2000".equals(tx2342Response.getCode())) {
	                    logger.info("[Message]=[" + tx2342Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2352".equals(txCode)) {
	                Tx2342Response tx2342Response = new Tx2342Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2342Response.getResponsePlainText());
	                if ("2000".equals(tx2342Response.getCode())) {
	                    logger.info("[Message]=[" + tx2342Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2354".equals(txCode)) {
	                Tx2354Response tx2354Response = new Tx2354Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2354Response.getResponsePlainText());
	                if ("2000".equals(tx2354Response.getCode())) {
	                    logger.info("[Message]=[" + tx2354Response.getMessage() + "]");
	                    // 处理业务
	                }
	            }else if ("2320".equals(txCode)) {// 身份验证
	                Tx2320Response tx2320Response = new Tx2320Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2320Response.getResponsePlainText());
	                if ("2000".equals(tx2320Response.getCode())) {
	                    logger.info("[Message]=[" + tx2320Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2392".equals(txCode)) {//
	                Tx2392Response tx2392Response = new Tx2392Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2392Response.getResponsePlainText());
	                if ("2000".equals(tx2392Response.getCode())) {
	                    logger.info("[Message]=[" + tx2392Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2501".equals(txCode)) {//
	                Tx2501Response tx2501Response = new Tx2501Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2501Response.getResponsePlainText());
	                if ("2000".equals(tx2501Response.getCode())) {
	                    logger.info("[Message]=[" + tx2501Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2502".equals(txCode)) {//
	                Tx2502Response tx2502Response = new Tx2502Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2502Response.getResponsePlainText());
	                if ("2000".equals(tx2502Response.getCode())) {
	                    logger.info("[Message]=[" + tx2502Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2503".equals(txCode)) {//
	                Tx2503Response tx2503Response = new Tx2503Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2503Response.getResponsePlainText());
	                if ("2000".equals(tx2503Response.getCode())) {
	                    logger.info("[Message]=[" + tx2503Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2511".equals(txCode)) {//
	                Tx2511Response tx2511Response = new Tx2511Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2511Response.getResponsePlainText());
	                if ("2000".equals(tx2511Response.getCode())) {
	                    logger.info("[Message]=[" + tx2511Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2512".equals(txCode)) {//
	                Tx2512Response tx2512Response = new Tx2512Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2512Response.getResponsePlainText());
	                if ("2000".equals(tx2512Response.getCode())) {
	                    logger.info("[Message]=[" + tx2512Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2521".equals(txCode)) {//
	                Tx2521Response tx2521Response = new Tx2521Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2521Response.getResponsePlainText());
	                if ("2000".equals(tx2521Response.getCode())) {
	                    logger.info("[Message]=[" + tx2521Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2522".equals(txCode)) {//
	                Tx2522Response tx2522Response = new Tx2522Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2522Response.getResponsePlainText());
	                if ("2000".equals(tx2522Response.getCode())) {
	                    logger.info("[Message]=[" + tx2522Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2531".equals(txCode)) {
	                Tx2531Response tx2531Response = new Tx2531Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2531Response.getResponsePlainText());
	                if ("2000".equals(tx2531Response.getCode())) {
	                	data=tx2531Response.getCode();
	                    logger.info("[Message]=[" + tx2531Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2532".equals(txCode)) {
	                Tx2532Response tx2532Response = new Tx2532Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2532Response.getResponsePlainText());
	                if ("2000".equals(tx2532Response.getCode())) {
	                	data=tx2532Response.getCode();
	                    logger.info("[Message]=[" + tx2532Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2541".equals(txCode)) {
	                Tx2541Response txResponse = new Tx2541Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2542".equals(txCode)) {
	                Tx2542Response tx2542Response = new Tx2542Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2542Response.getResponsePlainText());
	                if ("2000".equals(tx2542Response.getCode())) {
	                    logger.info("[Message]=[" + tx2542Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2553".equals(txCode)) {
	                Tx2553Response tx2553Response = new Tx2553Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2553Response.getResponsePlainText());
	                if ("2000".equals(tx2553Response.getCode())) {
	                    logger.info("[Message]=[" + tx2553Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2561".equals(txCode)) { // 绑定并支付后台模式（无验证短信）
	                Tx2561Response txResponse = new Tx2561Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2562".equals(txCode)) { // 绑定并支付后台模式（发送验证短信）
	                Tx2562Response txResponse = new Tx2562Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2563".equals(txCode)) { // 绑定并支付后台模式（验证并支付）
	                Tx2563Response txResponse = new Tx2563Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2571".equals(txCode)) { // 账户验证
	                Tx2571Response txResponse = new Tx2571Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2572".equals(txCode)) { // 刷脸验证
	                Tx2572Response txResponse = new Tx2572Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2573".equals(txCode)) { // 发送短信
	                Tx2573Response txResponse = new Tx2573Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2574".equals(txCode)) { // 短信验证
	                Tx2574Response txResponse = new Tx2574Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2575".equals(txCode)) { // 协议签署
	                Tx2575Response txResponse = new Tx2575Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    logFields(txResponse);
	                }
	            } else if ("2601".equals(txCode)) {// 白名单批量上传
	                Tx2601Response tx2601Response = new Tx2601Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2601Response.getResponsePlainText());
	                if ("2000".equals(tx2601Response.getCode())) {
	                    logger.info("[Message]=[" + tx2601Response.getMessage() + "]");
	                }
	            } else if ("2603".equals(txCode)) { // 白名单上传批量查询
	                Tx2603Response tx2603Response = new Tx2603Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2603Response.getResponsePlainText());
	                if ("2000".equals(tx2603Response.getCode())) {
	                    logger.info("[Message]=[" + tx2603Response.getMessage() + "]");
	                    if (tx2603Response.getItemList() != null) {
	                        int size = tx2603Response.getItemList().size();
	                        for (int i = 0; i < size; i++) {
	                            Item item = tx2603Response.getItemList().get(i);
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[AccountNumber]=[" + item.getAccountNumber() + "]");
	                            logger.info("[AccountName]=[" + item.getAccountName() + "]");
	                            logger.info("[AccountType]=[" + item.getAccountType() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                            logger.info("[Note]=[" + item.getNote() + "]");
	                        }

	                    }
	                }
	            } else if ("2605".equals(txCode)) {// 白名单单笔查询
	                Tx2605Response tx2605Response = new Tx2605Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2605Response.getResponsePlainText());
	                if ("2000".equals(tx2605Response.getCode())) {
	                    logger.info("[Message]=[" + tx2605Response.getMessage() + "]");
	                }
	            } else if ("2611".equals(txCode)) {// 白名单单笔查询
	                Tx2611Response tx2611Response = new Tx2611Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2611Response.getResponsePlainText());
	                if ("2000".equals(tx2611Response.getCode())) {
	                    logger.info("[Message]=[" + tx2611Response.getMessage() + "]");
	                }
	            } else if ("2711".equals(txCode)) {
	                Tx2711Response tx2711Response = new Tx2711Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2711Response.getResponsePlainText());
	                if ("2000".equals(tx2711Response.getCode())) {
	                    logger.info("[Message]=[" + tx2711Response.getMessage() + "]");
	                }
	            } else if ("2713".equals(txCode)) {// 协议签署结果查询
	                Tx2713Response tx2713Response = new Tx2713Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2713Response.getResponsePlainText());
	                if ("2000".equals(tx2713Response.getCode())) {
	                    logger.info("[Message]=[" + tx2713Response.getMessage() + "]");
	                }
	            }else if ("2901".equals(txCode)) {// 刷脸图片上传
	                Tx2901Response tx2901Response = new Tx2901Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2901Response.getResponsePlainText());
	                if ("2000".equals(tx2901Response.getCode())) {
	                    logger.info("[Message]=[" + tx2901Response.getMessage() + "]");
	                }
	            } else if ("2903".equals(txCode)) {// 单个文件下载
	                Tx2903Response tx2903Response = new Tx2903Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2903Response.getResponsePlainText());
	                if ("2000".equals(tx2903Response.getCode())) {
	                    logger.info("[Message]=[" + tx2903Response.getMessage() + "]");
	                    String content = tx2903Response.getContent();
	                    request.setAttribute("content", content);

	                }
	            } else if ("2904".equals(txCode)) {// 批量文件下载
	                Tx2904Response tx2904Response = new Tx2904Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2904Response.getResponsePlainText());
	                if ("2000".equals(tx2904Response.getCode())) {
	                    logger.info("[Message]=[" + tx2904Response.getMessage() + "]");
	                    String content = tx2904Response.getContent();
	                    request.setAttribute("content", content);
	                }
	            } else if ("2911".equals(txCode)) {// 电子回单单笔查询
	                Tx2911Response tx2911Response = new Tx2911Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2911Response.getResponsePlainText());
	                if ("2000".equals(tx2911Response.getCode())) {
	                    logger.info("[Message]=[" + tx2911Response.getMessage() + "]");
	                    String content = tx2911Response.getContent();
	                    request.setAttribute("content", content);

	                }
	            } else if ("3116".equals(txCode)) {// P2P支付查询
	                Tx3116Response tx3116Response = new Tx3116Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3116Response.getResponsePlainText());
	                if ("2000".equals(tx3116Response.getCode())) {
	                    logger.info("[Message]=[" + tx3116Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3141".equals(txCode)) {// P2P还款
	                Tx3141Response tx3141Response = new Tx3141Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3141Response.getResponsePlainText());
	                if ("2000".equals(tx3141Response.getCode())) {
	                    logger.info("[Message]=[" + tx3141Response.getMessage() + "]");
	                    if (tx3141Response.getStatus() == 30 || tx3141Response.getStatus() == 40) {
	                        logger.info("[RepaymentTime]=[" + tx3141Response.getRepaymentTime() + "]");
	                    }
	                    // 处理业务
	                }
	            } else if ("3131".equals(txCode)) {// P2P项目结算
	                Tx3131Response tx3131Response = new Tx3131Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3131Response.getResponsePlainText());
	                if ("2000".equals(tx3131Response.getCode())) {
	                    logger.info("[Message]=[" + tx3131Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3136".equals(txCode)) {// P2P项目结算查询
	                Tx3136Response tx3136Response = new Tx3136Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3136Response.getResponsePlainText());
	                if ("2000".equals(tx3136Response.getCode())) {
	                    logger.info("[Message]=[" + tx3136Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3137".equals(txCode)) {// P2P项目账户转账结算
	                Tx3137Response txResponse = new Tx3137Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("3138".equals(txCode)) {// P2P项目账户转账结算查询
	                Tx3138Response txResponse = new Tx3138Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("3146".equals(txCode)) {// P2P还款查询
	                Tx3146Response tx3146Response = new Tx3146Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3146Response.getResponsePlainText());
	                if ("2000".equals(tx3146Response.getCode())) {
	                    logger.info("[Message]=[" + tx3146Response.getMessage() + "]");
	                    if (tx3146Response.getAccountType() == 11 || tx3146Response.getAccountType() == 12) {
	                        logger.info("[BankID]=[" + tx3146Response.getBankID() + "]");
	                        logger.info("[BankAccountName]=[" + tx3146Response.getBankAccountName() + "]");
	                        logger.info("[BankAccountNumber]=[" + tx3146Response.getBankAccountNumber() + "]");
	                        logger.info("[BankProvince]=[" + tx3146Response.getBankProvince() + "]");
	                        logger.info("[BankCity]=[" + tx3146Response.getBankCity() + "]");
	                    } else if (tx3146Response.getAccountType() == 20) {
	                        logger.info("[PaymentAccountName]=[" + tx3146Response.getPaymentAccountName() + "]");
	                        logger.info("[PaymentAccountNumber]=[" + tx3146Response.getPaymentAccountNumber() + "]");
	                    }
	                    if (tx3146Response.getStatus() == 30 || tx3146Response.getStatus() == 40) {
	                        logger.info("[RepaymentTime]=[" + tx3146Response.getRepaymentTime() + "]");
	                    }
	                    // 处理业务
	                }
	            } else if ("3151".equals(txCode)) {// P2P字段投资
	                Tx3151Response tx3151Response = new Tx3151Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3151Response.getResponsePlainText());
	                if ("2000".equals(tx3151Response.getCode())) {
	                    logger.info("[Message]=[" + tx3151Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3156".equals(txCode)) {// P2P字段投资
	                Tx3156Response tx3156Response = new Tx3156Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3156Response.getResponsePlainText());
	                if ("2000".equals(tx3156Response.getCode())) {
	                    logger.info("[Message]=[" + tx3156Response.getMessage() + "]");
	                }
	            } else if ("3161".equals(txCode)) {// P2P项目批量结算（基本户）
	                Tx3161Response tx3161Response = new Tx3161Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3161Response.getResponsePlainText());
	                if ("2000".equals(tx3161Response.getCode())) {
	                    logger.info("[Message]=[" + tx3161Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3162".equals(txCode)) {// P2P项目批量结算查询（基本户）
	                Tx3162Response tx3162Response = new Tx3162Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3162Response.getResponsePlainText());
	                if ("2000".equals(tx3162Response.getCode())) {
	                    logger.info("[Message]=[" + tx3162Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3251".equals(txCode)) {
	                Tx3251Response tx3251Response = new Tx3251Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3251Response.getResponsePlainText());
	                if ("2000".equals(tx3251Response.getCode())) {
	                    logger.info("[Message]=[" + tx3251Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3256".equals(txCode)) {
	                Tx3256Response tx3256Response = new Tx3256Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3256Response.getResponsePlainText());
	                if ("2000".equals(tx3256Response.getCode())) {
	                    logger.info("[Message]=[" + tx3256Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3291".equals(txCode)) {// P2P到账
	                Tx3291Response tx3291Response = new Tx3291Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3291Response.getResponsePlainText());
	                if ("2000".equals(tx3291Response.getCode())) {
	                    logger.info("[Message]=[" + tx3291Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3292".equals(txCode)) {// P2P到账查询
	                Tx3292Response tx3292Response = new Tx3292Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3292Response.getResponsePlainText());
	                if ("2000".equals(tx3292Response.getCode())) {
	                    logger.info("[Message]=[" + tx3292Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3310".equals(txCode)) {// 优惠券批量下发
	                Tx3310Response tx3310Response = new Tx3310Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3310Response.getResponsePlainText());
	                if ("2000".equals(tx3310Response.getCode())) {
	                    logger.info("[Message]=[" + tx3310Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3312".equals(txCode)) {// 优惠券批量查询
	                Tx3312Response tx3312Response = new Tx3312Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3312Response.getResponsePlainText());
	                if ("2000".equals(tx3312Response.getCode())) {
	                    logger.info("[Message]=[" + tx3312Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3315".equals(txCode)) {// 优惠券过期回收
	                Tx3315Response tx3315Response = new Tx3315Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3315Response.getResponsePlainText());
	                if ("2000".equals(tx3315Response.getCode())) {
	                    logger.info("[Message]=[" + tx3315Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3216".equals(txCode)) {// P2P项目支付查询（托管户）
	                Tx3216Response tx3216Response = new Tx3216Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3216Response.getResponsePlainText());
	                if ("2000".equals(tx3216Response.getCode())) {
	                    logger.info("[Message]=[" + tx3216Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3231".equals(txCode)) {// P2P项目结算（托管户）
	                Tx3231Response tx3231Response = new Tx3231Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3231Response.getResponsePlainText());
	                if ("2000".equals(tx3231Response.getCode())) {
	                    logger.info("[Message]=[" + tx3231Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3236".equals(txCode)) {// P2P项目结算查询（托管户）
	                Tx3236Response tx3236Response = new Tx3236Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3236Response.getResponsePlainText());
	                if ("2000".equals(tx3236Response.getCode())) {
	                    logger.info("[Message]=[" + tx3236Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3237".equals(txCode)) {// P2P项目账户转账结算（专属户）
	                Tx3237Response txResponse = new Tx3237Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("3238".equals(txCode)) {// P2P项目账户转账结算查询（专属户）
	                Tx3238Response txResponse = new Tx3238Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("3241".equals(txCode)) {// P2P还款
	                Tx3241Response tx3241Response = new Tx3241Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3241Response.getResponsePlainText());
	                if ("2000".equals(tx3241Response.getCode())) {
	                    logger.info("[Message]=[" + tx3241Response.getMessage() + "]");
	                    if (tx3241Response.getStatus() == 30 || tx3241Response.getStatus() == 40) {
	                        logger.info("[RepaymentTime]=[" + tx3241Response.getRepaymentTime() + "]");
	                    }
	                }
	            } else if ("3246".equals(txCode)) {// P2P还款查询
	                Tx3246Response tx3246Response = new Tx3246Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3246Response.getResponsePlainText());
	                if ("2000".equals(tx3246Response.getCode())) {
	                    logger.info("[Message]=[" + tx3246Response.getMessage() + "]");
	                    if (tx3246Response.getAccountType() == 11 || tx3246Response.getAccountType() == 12) {
	                        logger.info("[BankID]=[" + tx3246Response.getBankID() + "]");
	                        logger.info("[BankAccountName]=[" + tx3246Response.getBankAccountName() + "]");
	                        logger.info("[BankAccountNumber]=[" + tx3246Response.getBankAccountNumber() + "]");
	                        logger.info("[BankProvince]=[" + tx3246Response.getBankProvince() + "]");
	                        logger.info("[BankCity]=[" + tx3246Response.getBankCity() + "]");
	                    } else if (tx3246Response.getAccountType() == 20) {
	                        logger.info("[PaymentAccountName]=[" + tx3246Response.getPaymentAccountName() + "]");
	                        logger.info("[PaymentAccountNumber]=[" + tx3246Response.getPaymentAccountNumber() + "]");
	                    }
	                    if (tx3246Response.getStatus() == 30 || tx3246Response.getStatus() == 40) {
	                        logger.info("[RepaymentTime]=[" + tx3246Response.getRepaymentTime() + "]");
	                    }
	                }
	            } else if ("3261".equals(txCode)) {// P2P项目批量结算（托管户）
	                Tx3261Response tx3261Response = new Tx3261Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3261Response.getResponsePlainText());
	                if ("2000".equals(tx3261Response.getCode())) {
	                    logger.info("[Message]=[" + tx3261Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3262".equals(txCode)) {// P2P项目批量结算查询（托管户）
	                Tx3262Response tx3262Response = new Tx3262Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3262Response.getResponsePlainText());
	                if ("2000".equals(tx3262Response.getCode())) {
	                    logger.info("[Message]=[" + tx3262Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4202".equals(txCode)) {// 统一账户注册结果查询
	                Tx4202Response txResponse = new Tx4202Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4206".equals(txCode)) {// 用户支付账户客户信息批量查询（统一户）
	                Tx4206Response tx4206Response = new Tx4206Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4206Response.getResponsePlainText());
	                if ("2000".equals(tx4206Response.getCode())) {
	                    logger.info("[Message]=[" + tx4206Response.getMessage() + "]");
	                }
	            } else if ("4211".equals(txCode)) {// 支付账户余额查询解约
	                Tx4211Response tx4211Response = new Tx4211Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4211Response.getResponsePlainText());
	                if ("2000".equals(tx4211Response.getCode())) {
	                    logger.info("[Message]=[" + tx4211Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4221".equals(txCode)) {// 支付账户扣款解约
	                Tx4221Response tx4221Response = new Tx4221Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4221Response.getResponsePlainText());
	                if ("2000".equals(tx4221Response.getCode())) {
	                    logger.info("[Message]=[" + tx4221Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4212".equals(txCode)) {// 支付账户余额查询
	                Tx4212Response tx4212Response = new Tx4212Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4212Response.getResponsePlainText());
	                if ("2000".equals(tx4212Response.getCode())) {
	                    logger.info("[Message]=[" + tx4212Response.getMessage() + "]");
	                    // 处理业务
	                }

	            } else if ("4232".equals(txCode)) {// 用户支付账户注册查询
	                Tx4232Response txResponse = new Tx4232Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4237".equals(txCode)) {// 用户支付账户注册查询
	                Tx4237Response tx4237Response = new Tx4237Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4237Response.getResponsePlainText());
	                if ("2000".equals(tx4237Response.getCode())) {
	                    logger.info("[Message]=[" + tx4237Response.getMessage() + "]");
	                }
	            } else if ("4236".equals(txCode)) {// 账户认证等级批量查询（专属）
	                Tx4236Response tx4236Response = new Tx4236Response(respMsg[0], respMsg[1]);

	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4236Response.getResponsePlainText());
	                if ("2000".equals(tx4236Response.getCode())) {
	                    logger.info("[Message]=[" + tx4236Response.getMessage() + "]");
	                }
	            } else if ("4238".equals(txCode)) {// 用户支付账户余额批量查询（专属账户）
	                Tx4238Response tx4238Response = new Tx4238Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4238Response.getResponsePlainText());
	                if ("2000".equals(tx4238Response.getCode())) {
	                    logger.info("[Message]=[" + tx4238Response.getMessage() + "]");
	                }
	            } else if ("4239".equals(txCode)) {// 账户信息查询
	                Tx4239Response tx4239Response = new Tx4239Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4239Response.getResponsePlainText());
	                if ("2000".equals(tx4239Response.getCode())) {
	                    logger.info("[Message]=[" + tx4239Response.getMessage() + "]");
	                }
	            } else if ("4244".equals(txCode)) {
	                Tx4244Response tx4244Response = new Tx4244Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4244Response.getResponsePlainText());

	                if ("2000".equals(tx4244Response.getCode())) {
	                    logger.info("[Message]=[" + tx4244Response.getMessage() + "]");
	                    if (tx4244Response.getPaymentBankAccountList() != null) {
	                        int size = tx4244Response.getPaymentBankAccountList().size();
	                        for (int i = 0; i < size; i++) {
	                            PaymentBankAccount item = tx4244Response.getPaymentBankAccountList().get(i);
	                            logger.info("[BankID]=[" + item.getBankID() + "]");
	                            logger.info("[BankAccountNumber]=[" + item.getBankAccountNumber() + "]");
	                            logger.info("[BindingSystemNo]=[" + item.getBindingSystemNo() + "]");
	                            logger.info("[Status]=[" + item.getStatus() + "]");
	                        }
	                    }
	                    // 处理业务
	                }
	            } else if ("4252".equals(txCode)) {// 用户支付账户充值查询（托管户）
	                Tx4252Response tx4252Response = new Tx4252Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4252Response.getResponsePlainText());
	                if ("2000".equals(tx4252Response.getCode())) {
	                    logger.info("[Message]=[" + tx4252Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4256".equals(txCode)) {// 用户支付账户提现查询（托管户）
	                Tx4256Response tx4256Response = new Tx4256Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4256Response.getResponsePlainText());
	                if ("2000".equals(tx4256Response.getCode())) {
	                    logger.info("[Message]=[" + tx4256Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4262".equals(txCode)) {// 支付账户扣款签约查询
	                Tx4232Response txResponse = new Tx4232Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4264".equals(txCode)) {// 支付账户扣款解约(托管户)
	                Tx4264Response tx4264Response = new Tx4264Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4264Response.getResponsePlainText());
	                if ("2000".equals(tx4264Response.getCode())) {
	                    logger.info("[Message]=[" + tx4264Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4273".equals(txCode)) {// 专属账户解约（移动端）
	                Tx4273Response tx4273Response = new Tx4273Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4273Response.getResponsePlainText());
	                if ("2000".equals(tx4273Response.getCode())) {
	                    logger.info("[Message]=[" + tx4273Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4276".equals(txCode)) {// 专属账户解约（移动端）
	                Tx4276Response tx4276Response = new Tx4276Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4276Response.getResponsePlainText());
	                if ("2000".equals(tx4276Response.getCode())) {
	                    logger.info("[Message]=[" + tx4276Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4280".equals(txCode)) {// P2P字段投资
	                Tx4280Response tx4280Response = new Tx4280Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4280Response.getResponsePlainText());
	                if ("2000".equals(tx4280Response.getCode())) {
	                    logger.info("[Message]=[" + tx4280Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4321".equals(txCode)) {// 自动投资签约查询
	                Tx4321Response txResponse = new Tx4321Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4328".equals(txCode)) {// 自动投资解约
	                Tx4328Response txResponse = new Tx4328Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4333".equals(txCode)) {// 统一账户解约
	                Tx4333Response txResponse = new Tx4333Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4336".equals(txCode)) { // 统一账户签约查询
	                Tx4336Response txResponse = new Tx4336Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("4510".equals(txCode)) {// 支付账户余额查询
	                Tx4510Response txResponse = new Tx4510Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4512".equals(txCode)) {// 机构支付账户交易明细查询
	                Tx4512Response txResponse = new Tx4512Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4514".equals(txCode)) {// 机构支付账户透支可用额度查询
	                Tx4514Response txResponse = new Tx4514Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            }else if ("4516".equals(txCode)) {// 机构支付账户交易明细查询
	                Tx4516Response txResponse = new Tx4516Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4524".equals(txCode)) {// 机构支付账户网银充值查询
	                Tx4524Response txResponse = new Tx4524Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4526".equals(txCode)) {// 机构支付账户代收充值
	                Tx4526Response txResponse = new Tx4526Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4528".equals(txCode)) {// 机构支付账户代收充值查询
	                Tx4528Response txResponse = new Tx4528Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4530".equals(txCode)) {// 机构支付账户单笔转账
	                Tx4530Response txResponse = new Tx4530Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4532".equals(txCode)) {// 机构支付账户单笔转账查询
	                Tx4532Response txResponse = new Tx4532Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4534".equals(txCode)) {
	                Tx4534Response txResponse = new Tx4534Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4536".equals(txCode)) {
	                Tx4536Response txResponse = new Tx4536Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4538".equals(txCode)) {
	                Tx4538Response txResponse = new Tx4538Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }

	            } else if ("4540".equals(txCode)) {
	                Tx4540Response txResponse = new Tx4540Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4542".equals(txCode)) {
	                Tx4542Response txResponse = new Tx4542Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4544".equals(txCode)) {
	                Tx4544Response txResponse = new Tx4544Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4546".equals(txCode)) {
	                Tx4546Response txResponse = new Tx4546Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                    // 处理业务
	                }

	            } else if ("7111".equals(txCode)) {// 投资人白名单上传
	                Tx7111Response txResponse = new Tx7111Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7112".equals(txCode)) {// 投资人白名单查询
	                Tx7112Response txResponse = new Tx7112Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7122".equals(txCode)) {// 投资人订单支付查询
	                Tx7122Response txResponse = new Tx7122Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7131".equals(txCode)) {// 投资人退款
	                Tx7131Response txResponse = new Tx7131Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7132".equals(txCode)) {// 投资人退款查询
	                Tx7132Response txResponse = new Tx7132Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7141".equals(txCode)) {// 融资人结算
	                Tx7141Response txResponse = new Tx7141Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7142".equals(txCode)) {// 融资人结算查询
	                Tx7142Response txResponse = new Tx7142Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7152".equals(txCode)) {// 融资人支付查询
	                Tx7152Response txResponse = new Tx7152Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7201".equals(txCode)) {// 建立绑定关系
	                Tx7201Response txResponse = new Tx7201Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7202".equals(txCode)) {// 查询绑定关系
	                Tx7202Response txResponse = new Tx7202Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7203".equals(txCode)) {// 解除绑定关系
	                Tx7203Response txResponse = new Tx7203Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7213".equals(txCode)) {// 投资人快捷支付
	                Tx7213Response txResponse = new Tx7213Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7214".equals(txCode)) {// 投资人Token支付（广州农商行）
	                Tx7214Response txResponse = new Tx7214Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7215".equals(txCode)) {// 结算（广州农商行）
	                Tx7215Response txResponse = new Tx7215Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7216".equals(txCode)) {// 结算查询（广州农商行）
	                Tx7216Response txResponse = new Tx7216Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7223".equals(txCode)) {// 投资人快捷支付查询
	                Tx7223Response txResponse = new Tx7223Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7220".equals(txCode)) {// 投资人支付查询
	                Tx7220Response txResponse = new Tx7220Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7229".equals(txCode)) {// 订单余额查询
	                Tx7229Response txResponse = new Tx7229Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7230".equals(txCode)) {// 订单查询
	                Tx7230Response txResponse = new Tx7230Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7231".equals(txCode)) {// 订单查询
	                Tx7231Response txResponse = new Tx7231Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7232".equals(txCode)) {// 订单查询
	                Tx7232Response txResponse = new Tx7232Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7233".equals(txCode)) {// 金额白名单上传
	                Tx7233Response txResponse = new Tx7233Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7234".equals(txCode)) {// 白名单查询
	                Tx7234Response txResponse = new Tx7234Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7241".equals(txCode)) {// 投资人退款
	                Tx724xResponse txResponse = new Tx724xResponse(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7242".equals(txCode)) {// 投资人退款查询
	                Tx7242Response txResponse = new Tx7242Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7243".equals(txCode)) {// 融资人结算
	                Tx724xResponse txResponse = new Tx724xResponse(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7244".equals(txCode)) {// 融资人结算查询
	                Tx7244Response txResponse = new Tx7244Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7246".equals(txCode)) {// 投资人快捷支付退款
	                Tx7246Response txResponse = new Tx7246Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7247".equals(txCode)) {// 投资人快捷支付退款查询
	                Tx7247Response txResponse = new Tx7247Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7253".equals(txCode)) {// 还款（仅支持还款订单结算）
	                Tx7253Response txResponse = new Tx7253Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7254".equals(txCode)) {// 结算（融资人结算）
	                Tx7254Response txResponse = new Tx7254Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7256".equals(txCode)) {// 投资人Token支付退款
	                Tx7256Response txResponse = new Tx7256Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7257".equals(txCode)) {// 投资人Token支付退款查询
	                Tx7257Response txResponse = new Tx7257Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7249".equals(txCode)) {// 订单划转
	                Tx7249Response txResponse = new Tx7249Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7252".equals(txCode)) {// 融资人支付查询
	                Tx7252Response txResponse = new Tx7252Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7253".equals(txCode)) {// 融资人结算
	                Tx7253Response txResponse = new Tx7253Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7261".equals(txCode)) {// 投资/还款订单单笔代收(代收中间户)
	                Tx7261Response txResponse = new Tx7261Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7262".equals(txCode)) {// 投资/还款订单单笔代收交易查询
	                Tx7262Response txResponse = new Tx7262Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7264".equals(txCode)) {// 投资代收退款
	                Tx7264Response txResponse = new Tx7264Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7265".equals(txCode)) {// 投资代收退款查询
	                Tx7265Response txResponse = new Tx7265Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7267".equals(txCode)) {// 投资/还款订单单笔代收(银行签名版)
	                Tx7267Response txResponse = new Tx7267Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7271".equals(txCode)) {// 中间户账户明细查询
	                Tx7271Response txResponse = new Tx7271Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7272".equals(txCode)) {
	                Tx7272Response txResponse = new Tx7272Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7273".equals(txCode)) {
	                Tx7273Response txResponse = new Tx7273Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7275".equals(txCode)) {// 投资/还款订单单笔代收(平准基金)
	                Tx7275Response txResponse = new Tx7275Response(respMsg[0], respMsg[1]);

	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7276".equals(txCode)) {// 投资/还款订单代收交易查询(平准基金)
	                Tx7276Response txResponse = new Tx7276Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7277".equals(txCode)) {// 结算(平准基金)
	                Tx7277Response txResponse = new Tx7277Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7278".equals(txCode)) {// 结算查询(平准基金)
	                Tx7278Response txResponse = new Tx7278Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7281".equals(txCode)) {
	                Tx7281Response txResponse = new Tx7281Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7282".equals(txCode)) {
	                Tx7282Response txResponse = new Tx7282Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7283".equals(txCode)) {
	                Tx7283Response txResponse = new Tx7283Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("7284".equals(txCode)) {
	                Tx7284Response txResponse = new Tx7284Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8112".equals(txCode)) {
	                Tx8112Response txResponse = new Tx8112Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8122".equals(txCode)) {
	                Tx8122Response txResponse = new Tx8122Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8123".equals(txCode)) {
	                Tx8123Response txResponse = new Tx8123Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8132".equals(txCode)) {
	                Tx8132Response txResponse = new Tx8132Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8133".equals(txCode)) {
	                Tx8133Response txResponse = new Tx8133Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8134".equals(txCode)) {
	                Tx8134Response txResponse = new Tx8134Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8142".equals(txCode)) {
	                Tx8142Response txResponse = new Tx8142Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8143".equals(txCode)) {
	                Tx8143Response txResponse = new Tx8143Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8101".equals(txCode)) {
	                Tx8101Response txResponse = new Tx8101Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8113".equals(txCode)) {
	                Tx8113Response txResponse = new Tx8113Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8145".equals(txCode)) {
	                Tx8145Response txResponse = new Tx8145Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8210".equals(txCode)) {
	                Tx8210Response txResponse = new Tx8210Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8211".equals(txCode)) {
	                Tx8211Response txResponse = new Tx8211Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8212".equals(txCode)) {
	                Tx8212Response txResponse = new Tx8212Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8213".equals(txCode)) {
	                Tx8213Response txResponse = new Tx8213Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8215".equals(txCode)) {
	                Tx8215Response txResponse = new Tx8215Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8220".equals(txCode)) {
	                Tx8220Response txResponse = new Tx8220Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8221".equals(txCode)) {
	                Tx8221Response txResponse = new Tx8221Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8222".equals(txCode)) {
	                Tx8222Response txResponse = new Tx8222Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8223".equals(txCode)) {
	                Tx8223Response txResponse = new Tx8223Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8230".equals(txCode)) {
	                Tx8230Response txResponse = new Tx8230Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8232".equals(txCode)) {
	                Tx8232Response txResponse = new Tx8232Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8242".equals(txCode)) {
	                Tx8242Response txResponse = new Tx8242Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8245".equals(txCode)) {
	                Tx8245Response txResponse = new Tx8245Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8246".equals(txCode)) {
	                Tx8246Response txResponse = new Tx8246Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8250".equals(txCode)) {
	                Tx8250Response txResponse = new Tx8250Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8251".equals(txCode)) {
	                Tx8251Response txResponse = new Tx8251Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8252".equals(txCode)) {
	                Tx8252Response txResponse = new Tx8252Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8253".equals(txCode)) {
	                Tx8253Response txResponse = new Tx8253Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8255".equals(txCode)) {
	                Tx8255Response txResponse = new Tx8255Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8260".equals(txCode)) {
	                Tx8260Response txResponse = new Tx8260Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8262".equals(txCode)) {
	                Tx8262Response txResponse = new Tx8262Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8270".equals(txCode)) {
	                Tx8270Response txResponse = new Tx8270Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("8272".equals(txCode)) {
	                Tx8272Response txResponse = new Tx8272Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", txResponse.getResponsePlainText());
	                if ("2000".equals(txResponse.getCode())) {
	                    logger.info("[Message]=[" + txResponse.getMessage() + "]");
	                }
	            } else if ("3601".equals(txCode)) {
	                Tx3601Response tx3601Response = new Tx3601Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3601Response.getResponsePlainText());
	                if ("2000".equals(tx3601Response.getCode())) {
	                    logger.info("[Message]=[" + tx3601Response.getMessage() + "]");
	                }
	            } else if ("3602".equals(txCode)) {
	                Tx3602Response tx3602Response = new Tx3602Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3602Response.getResponsePlainText());
	                if ("2000".equals(tx3602Response.getCode())) {
	                    logger.info("[Message]=[" + tx3602Response.getMessage() + "]");
	                }
	            } else if ("3612".equals(txCode)) {// P2P支付查询 (未全打印)
	                Tx3612Response tx3612Response = new Tx3612Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3612Response.getResponsePlainText());
	                if ("2000".equals(tx3612Response.getCode())) {
	                    logger.info("[Message]=[" + tx3612Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3618".equals(txCode)) {// P2P支付状态变更通知
	                Tx3618Response tx3618Response = new Tx3618Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3618Response.getResponsePlainText());
	                if ("2000".equals(tx3618Response.getCode())) {
	                    logger.info("[Message]=[" + tx3618Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3631".equals(txCode)) {// P2P项目结算
	                Tx3631Response tx3631Response = new Tx3631Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3631Response.getResponsePlainText());
	                if ("2000".equals(tx3631Response.getCode())) {
	                    logger.info("[Message]=[" + tx3631Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3632".equals(txCode)) {// P2P项目结算查询
	                Tx3632Response tx3632Response = new Tx3632Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3632Response.getResponsePlainText());
	                if ("2000".equals(tx3632Response.getCode())) {
	                    logger.info("[Message]=[" + tx3632Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3641".equals(txCode)) {// P2P项目还款（从融资人银行账户）
	                Tx3641Response tx3641Response = new Tx3641Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3641Response.getResponsePlainText());
	                if ("2000".equals(tx3641Response.getCode())) {
	                    logger.info("[Message]=[" + tx3641Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3642".equals(txCode)) {// P2P项目还款查询
	                Tx3642Response tx3642Response = new Tx3642Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3642Response.getResponsePlainText());
	                if ("2000".equals(tx3642Response.getCode())) {
	                    logger.info("[Message]=[" + tx3642Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3643".equals(txCode)) {// P2P项目还款（从担保公司或平台的支付账户）
	                Tx3643Response tx3643Response = new Tx3643Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3643Response.getResponsePlainText());
	                if ("2000".equals(tx3643Response.getCode())) {
	                    logger.info("[Message]=[" + tx3643Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("3644".equals(txCode)) {// P2P项目还款（从担保公司或平台的支付账户）
	                Tx3644Response tx3644Response = new Tx3644Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx3644Response.getResponsePlainText());
	                if ("2000".equals(tx3644Response.getCode())) {
	                    logger.info("[Message]=[" + tx3644Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("5111".equals(txCode)) {// 境外收款方账户
	                Tx5111Response tx5111Response = new Tx5111Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5111Response.getResponsePlainText());
	                if ("2000".equals(tx5111Response.getCode())) {
	                    logger.info("[Message]=[" + tx5111Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4701".equals(txCode)) {// 电子账户开户
	                Tx4701Response tx4701Response = new Tx4701Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4701Response.getResponsePlainText());
	                if ("2000".equals(tx4701Response.getCode())) {
	                    logger.info("[Message]=[" + tx4701Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4702".equals(txCode)) {// 电子账户注册查询
	                Tx4702Response tx4702Response = new Tx4702Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4702Response.getResponsePlainText());
	                if ("2000".equals(tx4702Response.getCode())) {
	                    logger.info("[Message]=[" + tx4702Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4704".equals(txCode)) {// 电子账户余额查询（徽商银行资金监管）
	                Tx4704Response tx4704Response = new Tx4704Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4704Response.getResponsePlainText());
	                if ("2000".equals(tx4704Response.getCode())) {
	                    logger.info("[Message]=[" + tx4704Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4705".equals(txCode)) {// 电子账户余额查询（徽商银行资金监管）
	                Tx4705Response tx4705Response = new Tx4705Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4705Response.getResponsePlainText());
	                if ("2000".equals(tx4705Response.getCode())) {
	                    logger.info("[Message]=[" + tx4705Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4711".equals(txCode)) {// 短信验证码发送接口
	                Tx4711Response tx4711Response = new Tx4711Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4711Response.getResponsePlainText());
	                if ("2000".equals(tx4711Response.getCode())) {
	                    logger.info("[Message]=[" + tx4711Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4721".equals(txCode)) {// 电子账户密码设置
	                Tx4721Response tx4721Response = new Tx4721Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4721Response.getResponsePlainText());

	                if ("2000".equals(tx4721Response.getCode())) {
	                    logger.info("[Message]=[" + tx4721Response.getMessage() + "]");

	                    String base64Form = tx4721Response.getForm();
	                    String form = Base64.decode(base64Form, StringUtil.DEFAULT_CHARSET);
	                    request.setAttribute("form", form);
	                    logger.info("[Message]=[" + form + "]");
	                    request.getRequestDispatcher("/AutoSubmit1.jsp").forward(request, response);
	                    return "";
	                    // 读取form自提交
	                }
	            } else if ("4722".equals(txCode)) {// 电子账户密码设置批量查询接口
	                Tx4722Response tx4722Response = new Tx4722Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4722Response.getResponsePlainText());
	                if ("2000".equals(tx4722Response.getCode())) {
	                    logger.info("[Message]=[" + tx4722Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4731".equals(txCode)) {// 个人银行卡绑定
	                Tx4731Response tx4731Response = new Tx4731Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4731Response.getResponsePlainText());
	                if ("2000".equals(tx4731Response.getCode())) {
	                    logger.info("[Message]=[" + tx4731Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4732".equals(txCode)) {// 个人银行卡绑定状态批量查询
	                Tx4732Response tx4732Response = new Tx4732Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4732Response.getResponsePlainText());
	                if ("2000".equals(tx4732Response.getCode())) {
	                    logger.info("[Message]=[" + tx4732Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4733".equals(txCode)) {// 个人银行卡解绑
	                Tx4733Response tx4733Response = new Tx4733Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4733Response.getResponsePlainText());
	                if ("2000".equals(tx4733Response.getCode())) {
	                    logger.info("[Message]=[" + tx4733Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4734".equals(txCode)) {// 银行卡解绑状态批量查询
	                Tx4734Response tx4734Response = new Tx4734Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4734Response.getResponsePlainText());
	                if ("2000".equals(tx4734Response.getCode())) {
	                    logger.info("[Message]=[" + tx4734Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4736".equals(txCode)) {// 个人银行卡预留手机号修改
	                Tx4736Response tx4736Response = new Tx4736Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4736Response.getResponsePlainText());
	                if ("2000".equals(tx4736Response.getCode())) {
	                    logger.info("[Message]=[" + tx4736Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4742".equals(txCode)) {// 电子账户充值（快捷充值）
	                Tx4742Response tx4742Response = new Tx4742Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4742Response.getResponsePlainText());
	                if ("2000".equals(tx4742Response.getCode())) {
	                    logger.info("[Message]=[" + tx4742Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4744".equals(txCode)) {// 电子账户充值查询
	                Tx4744Response tx4744Response = new Tx4744Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4744Response.getResponsePlainText());
	                if ("2000".equals(tx4744Response.getCode())) {
	                    logger.info("[Message]=[" + tx4744Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4751".equals(txCode)) {// 电子账户提现
	                Tx4751Response tx4751Response = new Tx4751Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4751Response.getResponsePlainText());
	                if ("2000".equals(tx4751Response.getCode())) {
	                    logger.info("[Message]=[" + tx4751Response.getMessage() + "]");
	                    String base64Form = tx4751Response.getForm();
	                    String form = Base64.decode(base64Form, StringUtil.DEFAULT_CHARSET);
	                    request.setAttribute("form", form);
	                    request.getRequestDispatcher("/AutoSubmit1.jsp").forward(request, response);
	                    return "";
	                    // 读取form自提交
	                }
	            } else if ("4752".equals(txCode)) {// 电子账户提现查询
	                Tx4752Response tx4752Response = new Tx4752Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4752Response.getResponsePlainText());
	                if ("2000".equals(tx4752Response.getCode())) {
	                    logger.info("[Message]=[" + tx4752Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4761".equals(txCode)) {// P2P项目信息发布
	                Tx4761Response tx4761Response = new Tx4761Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4761Response.getResponsePlainText());
	                if ("2000".equals(tx4761Response.getCode())) {
	                    logger.info("[Message]=[" + tx4761Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4762".equals(txCode)) {// P2P项目信息查询
	                Tx4762Response tx4762Response = new Tx4762Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4762Response.getResponsePlainText());
	                if ("2000".equals(tx4762Response.getCode())) {
	                    logger.info("[Message]=[" + tx4762Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4771".equals(txCode)) {// P2P项目投资支付
	                Tx4771Response tx4771Response = new Tx4771Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4771Response.getResponsePlainText());
	                if ("2000".equals(tx4771Response.getCode())) {
	                    logger.info("[Message]=[" + tx4771Response.getMessage() + "]");
	                    String base64Form = tx4771Response.getForm();
	                    String form = Base64.decode(base64Form, StringUtil.DEFAULT_CHARSET);
	                    request.setAttribute("form", form);
	                    request.getRequestDispatcher("/AutoSubmit1.jsp").forward(request, response);
	                    return "";
	                    // 读取form自提交
	                }
	            } else if ("4772".equals(txCode)) {// P2P项目投资支付查询
	                Tx4772Response tx4772Response = new Tx4772Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4772Response.getResponsePlainText());
	                if ("2000".equals(tx4772Response.getCode())) {
	                    logger.info("[Message]=[" + tx4772Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4774".equals(txCode)) {// P2P项目自动投资
	                Tx4774Response tx4774Response = new Tx4774Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4774Response.getResponsePlainText());
	                if ("2000".equals(tx4774Response.getCode())) {
	                    logger.info("[Message]=[" + tx4774Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4775".equals(txCode)) {// P2P项目投资撤销
	                Tx4775Response tx4775Response = new Tx4775Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4775Response.getResponsePlainText());
	                if ("2000".equals(tx4775Response.getCode())) {
	                    logger.info("[Message]=[" + tx4775Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4776".equals(txCode)) {// P2P项目自动投资查询
	                Tx4776Response tx4776Response = new Tx4776Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4776Response.getResponsePlainText());
	                if ("2000".equals(tx4776Response.getCode())) {
	                    logger.info("[Message]=[" + tx4776Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4781".equals(txCode)) {// P2P项目自动投资签约
	                Tx4781Response tx4781Response = new Tx4781Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4781Response.getResponsePlainText());
	                if ("2000".equals(tx4781Response.getCode())) {
	                    logger.info("[Message]=[" + tx4781Response.getMessage() + "]");
	                    String base64Form = tx4781Response.getForm();
	                    String form = Base64.decode(base64Form, StringUtil.DEFAULT_CHARSET);
	                    request.setAttribute("form", form);
	                    request.getRequestDispatcher("/AutoSubmit1.jsp").forward(request, response);
	                    return "";
	                    // 读取form自提交
	                }
	            } else if ("4782".equals(txCode)) {// P2P项目自动投资签约查询
	                Tx4782Response tx4782Response = new Tx4782Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4782Response.getResponsePlainText());
	                if ("2000".equals(tx4782Response.getCode())) {
	                    logger.info("[Message]=[" + tx4782Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4784".equals(txCode)) {// P2P项目自动投资签约取消
	                Tx4784Response tx4784Response = new Tx4784Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4784Response.getResponsePlainText());
	                if ("2000".equals(tx4784Response.getCode())) {
	                    logger.info("[Message]=[" + tx4784Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4791".equals(txCode)) {// P2P项目债权转让
	                Tx4791Response tx4791Response = new Tx4791Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4791Response.getResponsePlainText());
	                if ("2000".equals(tx4791Response.getCode())) {
	                    logger.info("[Message]=[" + tx4791Response.getMessage() + "]");
	                    String base64Form = tx4791Response.getForm();
	                    String form = Base64.decode(base64Form, StringUtil.DEFAULT_CHARSET);
	                    request.setAttribute("form", form);
	                    request.getRequestDispatcher("/AutoSubmit1.jsp").forward(request, response);
	                    return "";
	                    // 读取form自提交
	                }
	            } else if ("4792".equals(txCode)) {// P2P债权转让查询
	                Tx4792Response tx4792Response = new Tx4792Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4792Response.getResponsePlainText());
	                if ("2000".equals(tx4792Response.getCode())) {
	                    logger.info("[Message]=[" + tx4792Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4801".equals(txCode)) {// P2P项目批量融资结算
	                Tx4801Response tx4801Response = new Tx4801Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4801Response.getResponsePlainText());
	                if ("2000".equals(tx4801Response.getCode())) {
	                    logger.info("[Message]=[" + tx4801Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4802".equals(txCode)) {// P2P项目批量融资结算查询
	                Tx4802Response tx4802Response = new Tx4802Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4802Response.getResponsePlainText());
	                if ("2000".equals(tx4802Response.getCode())) {
	                    logger.info("[Message]=[" + tx4802Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4803".equals(txCode)) {// P2P项目还款冻结/冻结撤销
	                Tx4803Response tx4803Response = new Tx4803Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4803Response.getResponsePlainText());
	                if ("2000".equals(tx4803Response.getCode())) {
	                    logger.info("[Message]=[" + tx4803Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4804".equals(txCode)) {// P2P项目还款冻结/冻结撤销查询
	                Tx4804Response tx4804Response = new Tx4804Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4804Response.getResponsePlainText());
	                if ("2000".equals(tx4804Response.getCode())) {
	                    logger.info("[Message]=[" + tx4804Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4811".equals(txCode)) {// P2P项目批量还款结算
	                Tx4811Response tx4811Response = new Tx4811Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4811Response.getResponsePlainText());
	                if ("2000".equals(tx4811Response.getCode())) {
	                    logger.info("[Message]=[" + tx4811Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4812".equals(txCode)) {// P2P项目批量还款结算查询
	                Tx4812Response tx4812Response = new Tx4812Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4812Response.getResponsePlainText());
	                if ("2000".equals(tx4812Response.getCode())) {
	                    logger.info("[Message]=[" + tx4812Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4813".equals(txCode)) {// 电子账户红包发放/撤销
	                Tx4813Response tx4813Response = new Tx4813Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4813Response.getResponsePlainText());
	                if ("2000".equals(tx4813Response.getCode())) {
	                    logger.info("[Message]=[" + tx4813Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4814".equals(txCode)) {// 电子账户红包发放/撤销结果查询
	                Tx4814Response tx4814Response = new Tx4814Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4814Response.getResponsePlainText());
	                if ("2000".equals(tx4814Response.getCode())) {
	                    logger.info("[Message]=[" + tx4814Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("4816".equals(txCode)) {// 电子账户交易对账
	                Tx4816Response tx4816Response = new Tx4816Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx4816Response.getResponsePlainText());
	                if ("2000".equals(tx4816Response.getCode())) {
	                    logger.info("[Message]=[" + tx4816Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("5116".equals(txCode)) {// 境外收款方账户查询
	                Tx5116Response tx5116Response = new Tx5116Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5116Response.getResponsePlainText());
	                if ("2000".equals(tx5116Response.getCode())) {
	                    logger.info("[Message]=[" + tx5116Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("5126".equals(txCode)) {// 跨境汇款支付查询
	                Tx5126Response tx5126Response = new Tx5126Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5126Response.getResponsePlainText());
	                if ("2000".equals(tx5126Response.getCode())) {
	                    logger.info("[Message]=[" + tx5126Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2811".equals(txCode)) {
	                Tx2811Response tx2811Response = new Tx2811Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2811Response.getResponsePlainText());
	                request.setAttribute("status", String.valueOf(tx2811Response.getStatus()));
	                request.setAttribute("imageUrl", tx2811Response.getImageUrl());
	                if ("2000".equals(tx2811Response.getCode())) {
	                    logger.info("[Message]=[" + tx2811Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2812".equals(txCode)) {
	                Tx2812Response tx2812Response = new Tx2812Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2812Response.getResponsePlainText());
	                if ("2000".equals(tx2812Response.getCode())) {
	                    logger.info("[Message]=[" + tx2812Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2813".equals(txCode)) {
	                Tx2813Response tx2813Response = new Tx2813Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2813Response.getResponsePlainText());
	                request.setAttribute("authCode", tx2813Response.getAuthCode());
	                if ("2000".equals(tx2813Response.getCode())) {
	                    logger.info("[Message]=[" + tx2813Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2814".equals(txCode)) {
	                Tx2814Response tx2814Response = new Tx2814Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2814Response.getResponsePlainText());
	                request.setAttribute("authCode", tx2814Response.getAuthCode());
	                if ("2000".equals(tx2814Response.getCode())) {
	                    logger.info("[Message]=[" + tx2814Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2820".equals(txCode)) {
	                Tx2820Response tx2820Response = new Tx2820Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2820Response.getResponsePlainText());
	                if ("2000".equals(tx2820Response.getCode())) {
	                    logger.info("[Message]=[" + tx2820Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2831".equals(txCode)) {
	                Tx2831Response tx2831Response = new Tx2831Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2831Response.getResponsePlainText());
	                if ("2000".equals(tx2831Response.getCode())) {
	                    logger.info("[Message]=[" + tx2831Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2832".equals(txCode)) {
	                Tx2832Response tx2832Response = new Tx2832Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2832Response.getResponsePlainText());
	                if ("2000".equals(tx2832Response.getCode())) {
	                    logger.info("[Message]=[" + tx2832Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2841".equals(txCode)) {
	                Tx2841Response tx2841Response = new Tx2841Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx2841Response.getResponsePlainText());
	                if ("2000".equals(tx2841Response.getCode())) {
	                    logger.info("[Message]=[" + tx2841Response.getMessage() + "]");
	                    // 处理业务
	                }
	            } else if ("2330".equals(txCode)) { // 短信下发（手机号）
	                Tx2330Response tx2330Response = new Tx2330Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx2330Response, txCode, txName);
	            } else if ("2331".equals(txCode)) { // 短信下发（支付账号）
	                Tx2331Response tx2331Response = new Tx2331Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx2331Response, txCode, txName);
	            } else if ("6101".equals(txCode)) { // 基金开户
	                Tx6101Response tx6101Response = new Tx6101Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6101Response, txCode, txName);
	            } else if ("6102".equals(txCode)) { // 开户查询
	                Tx6102Response tx6102Response = new Tx6102Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6102Response, txCode, txName);
	            } else if ("6111".equals(txCode)) { // 绑卡
	                Tx6111Response tx6111Response = new Tx6111Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6111Response, txCode, txName);
	            } else if ("6112".equals(txCode)) { // 绑卡查询
	                Tx6112Response tx6112Response = new Tx6112Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6112Response, txCode, txName);
	            } else if ("6116".equals(txCode)) { // 解绑
	                Tx6116Response tx6116Response = new Tx6116Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6116Response, txCode, txName);
	            } else if ("6121".equals(txCode)) { // 充值
	                Tx6121Response tx6121Response = new Tx6121Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6121Response, txCode, txName);
	            } else if ("6122".equals(txCode)) { // 充值查询
	                Tx6122Response tx6122Response = new Tx6122Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6122Response, txCode, txName);
	            } else if ("6124".equals(txCode)) { // 实时代扣
	                Tx6124Response tx6124Response = new Tx6124Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6124Response, txCode, txName);
	            } else if ("6125".equals(txCode)) { // 实时代扣查询
	                Tx6125Response tx6125Response = new Tx6125Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6125Response, txCode, txName);
	            } else if ("6126".equals(txCode)) { // 提现
	                Tx6126Response tx6126Response = new Tx6126Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6126Response, txCode, txName);
	            } else if ("6127".equals(txCode)) { // 提现查询
	                Tx6127Response tx6127Response = new Tx6127Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6127Response, txCode, txName);
	            } else if ("6131".equals(txCode)) { // 账户资产查询
	                Tx6131Response tx6131Response = new Tx6131Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6131Response, txCode, txName);
	            } else if ("6132".equals(txCode)) { // 基金参数查询
	                Tx6132Response tx6132Response = new Tx6132Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6132Response, txCode, txName);
	            } else if ("6133".equals(txCode)) { // 基金参数查询
	                Tx6133Response tx6133Response = new Tx6133Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6133Response, txCode, txName);
	            } else if ("6134".equals(txCode)) { // 基金参数查询
	                Tx6134Response tx6134Response = new Tx6134Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6134Response, txCode, txName);
	            } else if ("6135".equals(txCode)) { // 基金参数查询
	                Tx6135Response tx6135Response = new Tx6135Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6135Response, txCode, txName);
	            } else if ("5511".equals(txCode)) { // 跨境B2B境内收款人
	                Tx5511Response tx5511Response = new Tx5511Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5511Response.getResponsePlainText());
	            } else if ("5512".equals(txCode)) { // 跨境B2B境内收款人查询
	                Tx5512Response tx5512Response = new Tx5512Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5512Response.getResponsePlainText());
	            } else if ("5513".equals(txCode)) { // 跨境B2B境内收款人批量提交
	                Tx5513Response tx5513Response = new Tx5513Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5513Response.getResponsePlainText());
	            } else if ("5514".equals(txCode)) { // 跨境B2B境内收款人批量查询
	                Tx5514Response tx5514Response = new Tx5514Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5514Response.getResponsePlainText());
	            } else if ("5515".equals(txCode)) { // 境内收款人文件上传
	                Tx5515Response tx5515Response = new Tx5515Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5515Response.getResponsePlainText());
	            } else if ("5516".equals(txCode)) { // B2C收款人文件处理结果查询（按批次号）
	                Tx5516Response tx5516Response = new Tx5516Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5516Response.getResponsePlainText());
	            } else if ("5611".equals(txCode)) { // 跨境B2B出口订单
	                Tx5611Response tx5611Response = new Tx5611Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5611Response.getResponsePlainText());
	            } else if ("5612".equals(txCode)) { // 跨境B2B出口订单查询
	                Tx5612Response tx5612Response = new Tx5612Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5612Response.getResponsePlainText());
	            } else if ("5616".equals(txCode)) { // 跨境B2B出口订单查询
	                Tx5616Response tx5616Response = new Tx5616Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5616Response.getResponsePlainText());
	            } else if ("5617".equals(txCode)) { // 跨境B2B出口订单查询
	                Tx5617Response tx5617Response = new Tx5617Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5617Response.getResponsePlainText());
	            } else if ("5618".equals(txCode)) { // 跨境B2B出口订单查询
	                Tx5618Response tx5618Response = new Tx5618Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5618Response.getResponsePlainText());
	            } else if ("5631".equals(txCode)) { // B2C收款指令批量分页提交
	                Tx5631Response tx5631Response = new Tx5631Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5631Response.getResponsePlainText());
	            } else if ("5632".equals(txCode)) { // B2C收款指令批量分页查询
	                Tx5632Response tx5632Response = new Tx5632Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5632Response.getResponsePlainText());
	            } else if ("5633".equals(txCode)) { // 订单还原明细上传通知
	                Tx5633Response tx5633Response = new Tx5633Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5633Response.getResponsePlainText());
	            } else if ("5635".equals(txCode)) { // B2C收款指令上传通知
	                Tx5635Response tx5635Response = new Tx5635Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5635Response.getResponsePlainText());
	            } else if ("5636".equals(txCode)) { // B2C收款指令处理结果查询
	                Tx5636Response tx5636Response = new Tx5636Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5636Response.getResponsePlainText());
	            } else if ("5637".equals(txCode)) { // 订单还原明细文件处理结果查询
	                Tx5637Response tx5637Response = new Tx5637Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5637Response.getResponsePlainText());
	            } else if ("5641".equals(txCode)) { // B2C收款指令上传通知(含境内收款方)
	                Tx5641Response tx5641Response = new Tx5641Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5641Response.getResponsePlainText());
	            } else if ("5642".equals(txCode)) { // B2C收款指令上传结果查询(含境内收款方)
	                Tx5642Response tx5642Response = new Tx5642Response(respMsg[0], respMsg[1]);
	                request.setAttribute("txCode", txCode);
	                request.setAttribute("txName", txName);
	                request.setAttribute("plainText", tx5642Response.getResponsePlainText());
	            } else if ("2703".equals(txCode)) {
	                Tx2703Response tx2703Response = new Tx2703Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx2703Response, txCode, txName);
	            } else if ("5621".equals(txCode)) {
	                Tx5621Response tx5621Response = new Tx5621Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx5621Response, txCode, txName);
	            } else if ("5622".equals(txCode)) {
	                Tx5622Response tx5622Response = new Tx5622Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx5622Response, txCode, txName);
	            } else if ("5623".equals(txCode)) {
	                Tx5623Response tx5623Response = new Tx5623Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx5623Response, txCode, txName);
	            } else if ("5624".equals(txCode)) {
	                Tx5624Response tx5624Response = new Tx5624Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx5624Response, txCode, txName);
	            } else if ("5626".equals(txCode)) {
	                Tx5626Response tx5626Response = new Tx5626Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx5626Response, txCode, txName);
	            } else if ("6011".equals(txCode)) {//Pos线下订单支付
	                Tx6011Response tx6011Response = new Tx6011Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6011Response, txCode, txName);
	            }else if ("6012".equals(txCode)) {//Pos支付查询
	                Tx6012Response tx6012Response = new Tx6012Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6012Response, txCode, txName);
	            }else if ("6013".equals(txCode)) {//Pos订单查询
	                Tx6013Response tx6013Response = new Tx6013Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6013Response, txCode, txName);
	            }else if ("6021".equals(txCode)) {//Pos撤销
	                Tx6021Response tx6021Response = new Tx6021Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6021Response, txCode, txName);
	            }else if ("6022".equals(txCode)) {//Pos撤销查询
	                Tx6022Response tx6022Response = new Tx6022Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6022Response, txCode, txName);
	            }else if ("6031".equals(txCode)) {//Pos退货
	                Tx6031Response tx6031Response = new Tx6031Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6031Response, txCode, txName);
	            }else if ("6032".equals(txCode)) {//Pos退货查询
	                Tx6032Response tx6032Response = new Tx6032Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6032Response, txCode, txName);
	            }else if ("6041".equals(txCode)) {//线上线下统一对账单
	                Tx6041Response tx6041Response = new Tx6041Response(respMsg[0], respMsg[1]);
	                setRequestAttribute(request, tx6041Response, txCode, txName);
	            }
	            if ("2813".equals(txCode)) {
	                // request.getRequestDispatcher("AccessToken/Tx2813wx.jsp").forward(request,
	                // response);
	                request.getRequestDispatcher("/Response2813.jsp").forward(request, response);
	            } else if ("1403".equals(txCode)) {
	                request.getRequestDispatcher("/Response1403.jsp").forward(request, response);
	            } else {
	                // 转向Response.jsp页面
	                //request.getRequestDispatcher("/Response.jsp").forward(request, response);
	            	return data;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            processException(request, response, e.getMessage());
	        }
		return data;
	}
	 public void setRequestAttribute(HttpServletRequest request, TxBaseResponse txResponse, String txCode, String txName) {
	        request.setAttribute("txCode", txCode);
	        request.setAttribute("txName", txName);
	        request.setAttribute("plainText", txResponse.getResponsePlainText());
	        if ("2000".equals(txResponse.getCode())) {
	            logger.info("[Message]=[" + txResponse.getMessage() + "]");
	            // 处理业务
	        }
	    }

	    private void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
	        try {
	            request.setAttribute("errorMessage", errorMessage);
	            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
	        } catch (Exception e) {
	        }
	    }

	    /**
	     * 打印交易响应中每个Field的日志
	     */
	    private <T> void logFields(T txResponse) throws IllegalArgumentException, IllegalAccessException {
	        Class<? extends Object> c = txResponse.getClass();
	        Field[] fs = c.getDeclaredFields();
	        for (Field f : fs) {
	            f.setAccessible(true);
	            logger.info("[" + f.getName() + "]=[" + f.get(txResponse) + "]");
	        }
	    }
}

//package com.neo.rpc.registercenter;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.LoggerFactory;
//
//import java.nio.charset.Charset;
//import java.util.*;
//import java.util.Map.Entry;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * zookeeper实现的注册中心<br/> Created on 2013-9-22 上午10:12:00
// * @author  litao(litao02@zhuche.com),update lhb
// * @since 3.2
// */
//public final class ZkRegistryCenter {
//	private static final Logger LOG = LoggerFactory.getLogger(ZkRegistryCenter.class);
//	private static List<ZkRegistryCenter> otherRregisterCenter = new ArrayList<ZkRegistryCenter>();
//	//注册中心标准实现类，初始化
//	private static ZkRegistryCenter registerCenter = null;
//	private static final Logger LOGGER = LoggerFactory.getLogger(ZkRegistryCenter.class);
//	private final String CLUSTER_PATH;
//	private final String ZKPREFIXPROJECT;
//	//服务提供路径
//	private final String[]  REGISTER_PATHS;
//	private static final int SLEEP_TIME = 500;
//	private static final String DIR_GRAY_LEVEL = "graylevel";
//
//	private  ZkSessionManager manager;
//	private  ZooKeeper zooKeeper;
//
//	//避免频繁的从zk直接获取url地址，当获取的地址为null 时，间隔5s 取一次
//	private Map<String,Long> updateGetZkUrlTimeMap = new ConcurrentHashMap<String, Long>();
//
//	// 在zk上注册的机器节点列表
//	private volatile Map<String,Map<String,List<String>>> invokerUrlMap = new ConcurrentHashMap<String, Map<String,List<String>>>();
//
//	private static final String SERVER_NODE_SPLIT = ":";
//    private static final DefaultConfigCenterManager centerManager;
//
//	static {
//		//默认的registerCenter
//		registerCenter = new ZkRegistryCenter(ZkUtils.PROJECT_PREFIX
//				+ "/cluster", loadServiceProviderPaths(),
//				ZkUtils.PROJECT_PREFIX + "/remoteServiceCenter/allprojects",
//				ZkUtils.getZkSessionManager());
//
//		List<ZKConfig> zkConifgs = getOthers();
//		if (zkConifgs != null) {
//			for (ZKConfig config : zkConifgs) {
//				ZkSessionManager manager = null;
//				try {
//					manager = new DefaultZkSessionManager(config.getServers()
//							.trim(), config.getTimeout());
//					ZkRegistryCenter registerCenter = new ZkRegistryCenter("/"
//							+ config.getPrefix() + "/cluster",
//							new String[] { config.getPrefix() }, "/"
//									+ config.getPrefix()
//									+ "/remoteServiceCenter/allprojects",
//							manager);
//					otherRregisterCenter.add(registerCenter);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
//		}
//        centerManager = DefaultConfigCenterManager.getInstance();
////        centerManager.addDataChangeListenerList(dataChangeListener);
//	}
//
//	private ZkRegistryCenter(String clusterPath, String[] registerPath, String zkPrefixProject, ZkSessionManager manager){
//		this.CLUSTER_PATH = clusterPath;
//		this.REGISTER_PATHS = registerPath;
//		this.ZKPREFIXPROJECT = zkPrefixProject;
//		this.manager = manager;
//		this.zooKeeper = manager.getZooKeeper();
//		ZkRegistryCenterConnectionListener listener = new ZkRegistryCenterConnectionListener(this);
//		manager.addConnectionListener(listener);
//	}
//
//	public static List<ZKConfig> getOthers(){
//
//		String conf = null;
//        try {
//        	DefaultConfigCenterManager configCenterManager = DefaultConfigCenterManager.getInstance();
//    		String key = GlobalMessage.getProjectName() + ".remote.otherCluster.zKConfig";
//    		ClientDataSource clientDataSource = configCenterManager.getConfCenterApi().getDataSourceByKey(key);
//			if(clientDataSource == null){
//				LOG.error("other zkConfig is null,key:"+key);
//				return null;
//			}
//			conf = clientDataSource.getSourceValue();
//			LOG.error("other zkConfig is:"+conf);
//        } catch (Exception e) {
//			LOG.error("error to call config center", e);
//        }
//
//        if (StringUtils.isBlank(conf)) {
//        	LOG.error("other zkConfig is blank");
//            return null;
//        }
//
//        try {
//            JSONObject jsonObject = JSONObject.parseObject(conf);
//            String zkClusterConf = jsonObject.getObject("data", String.class);
//            if (StringUtils.isBlank(zkClusterConf)) {
//            	LOG.error("other zkConfig data is blank");
//                return null;
//            }
//
//            List<ZKConfig> zkConigs = JSONArray.parseArray(zkClusterConf, ZKConfig.class);
//            return zkConigs;
//        } catch (Exception e) {
//            LOG.info("zk conf exception", e);
//        }
//
//        return null;
//	}
//
//	public static class ZKConfig {
//		private String servers;
//		private int timeout;
//		private String prefix;
//		public String getServers() {
//			return servers;
//		}
//		public void setServers(String servers) {
//			this.servers = servers;
//		}
//		public int getTimeout() {
//			return timeout;
//		}
//		public void setTimeout(int timeout) {
//			this.timeout = timeout;
//		}
//		public String getPrefix() {
//			return prefix;
//		}
//		public void setPrefix(String prefix) {
//			this.prefix = prefix;
//		}
//	}
//
//	private Watcher watcher = new Watcher() {
//		public void process(WatchedEvent event) {
//			if (event.getType() == EventType.NodeChildrenChanged) {
//				try {
//					updateServerList(event.getPath());
//				} catch (Exception e) {
//					LOGGER.error("",e);
//					throw new FrameworkRuntimeException("更新子节点异常!");
//				}
//			}
//			if(event.getType() == EventType.None){
//				reloadZkInstance();
//			}
//		}
//	};
//
//
//	public static ZkRegistryCenter getInstance(){
//		return registerCenter ;
//	}
//
//	public static List<ZkRegistryCenter> getOtherZkRegistryCenter(){
//		return otherRregisterCenter;
//	}
//	/**
//	 * 加载 配置提供服务的zk路径
//	 * 默认只提供zuche的服务
//	 * <br/> Created on 2014-10-11 上午11:11:27
//	 * @author  李洪波(hb.li@zhuche.com)
//	 * @since 3.4
//	 * @return
//	 */
//	private static String[] loadServiceProviderPaths(){
//
//		Properties ps = PropertiesReader.getProperties("serviceManager");
//		String prefix = ZkUtils.PROJECT_PREFIX.substring(1, ZkUtils.PROJECT_PREFIX.length());
//		if(ps == null){
//
//			return new String[]{prefix};
//		}
//
//		return ps.getProperty("service.provide.path", prefix).trim().split(",");
//	}
//
//	/**
//	 * 服务器节点注册<br/> Created on 2013-9-22 上午10:13:21
//	 * @author  litao(litao02@zhuche.com)
//	 * @since 3.2
//	 * @param invokerUrl
//	 */
//	public synchronized void register(String invokerUrl,boolean isFirstStart){
//
//		LOG.info("execute region and update serverlist...url:"+invokerUrl+", isFirstStart:"+isFirstStart);
//		//zooKeeper.register(watcher);
//		for(int i =0 ;i< REGISTER_PATHS.length ; i++){
//			String newPath ="/"+ REGISTER_PATHS[i] +"/cluster";
//			createParentDirectory(newPath);
//			createServerNode(newPath, invokerUrl,isFirstStart);
//		}
//
//		updateServerList(CLUSTER_PATH);
//
//		//WeightHolder.updateWeightList(invokerUrlMap);//刷新权重集合缓存
//	}
//	// 如果不存在则创建顶层目录
//	private synchronized void createParentDirectory(String path) {
//		try {
//			if (zooKeeper.exists(path, false) == null) {
//				String[] paths = path.split("/");
//				String part = "";
//				for (int i = 1; i < paths.length; i++) {
//					part = part + "/" + paths[i];
//					if (zooKeeper.exists(part, false) == null) {
//						zooKeeper.create(part, new byte[0],ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//					}
//				}
//			}
//
//		} catch (KeeperException e) {
//			try {
//				Thread.sleep(SLEEP_TIME);
//				reloadZkInstance();
//			} catch (InterruptedException e1) {
//				LOGGER.error("",e1);
//			}
//		}catch (Exception e) {
//			LOGGER.error("",e);
//			throw new FrameworkRuntimeException("创建父目录异常!");
//		}
//	}
//
//	private synchronized void createServerNode(String path, String invokerUrl,boolean isFirstStart){
//
//		LOG.info("create service node,path:"+path+", invokerUrl:"+invokerUrl+", isFirstStart:"+isFirstStart);
//
//		/**
//		 * 将结点名称改为ip:projectName的形式，便于直接从结点获取服务端的url
//		 */
//		String serverNode = invokerUrl.replace("/", SERVER_NODE_SPLIT);
//		try {
//			if (zooKeeper.exists(path + "/" + serverNode, false) == null) {
//				zooKeeper.create(path + "/" + serverNode,invokerUrl.getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//			}else if(isFirstStart){
//				zooKeeper.delete(path + "/" + serverNode, -1);
//				zooKeeper.create(path + "/" + serverNode,invokerUrl.getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//			}
//		} catch (KeeperException e) {
//			//第一次启动时，如果不成功，直接抛出异常，启动失败
//			if(isFirstStart){
//				if(e instanceof KeeperException.NoNodeException){
//					try {
//						zooKeeper.create(path + "/" + serverNode,invokerUrl.getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//					} catch (Exception e1) {
//						LOGGER.error("",e);
//						throw new FrameworkRuntimeException("创建子目录异常!");
//					}
//				}else{
//					LOGGER.error("",e);
//					throw new FrameworkRuntimeException("创建子目录异常!");
//				}
//			}else{//
//				try {
//					Thread.sleep(SLEEP_TIME);
//					reloadZkInstance();
//				} catch (InterruptedException e1) {
//					LOGGER.error("",e1);
//				}
//			}
//
//		}catch (Exception e) {
//			LOGGER.error("",e);
//			throw new FrameworkRuntimeException("创建子目录异常!");
//		}
//	}
//
//	/**
//	 * 获取并监听groupNode的子节点变化,watch参数为true, 表示监听子节点变化事件.
//	 * 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册<br/> Created on 2013-9-18 下午3:55:14
//	 * @author  litao(litao02@zhuche.com)
//	 * @since 3.2
//	 * @param path
//	 * @throws Exception
//	 */
//	private synchronized void updateServerList(String path){
//		LOG.info("============path :"+path);
//		try {
//			Map<String,Map<String,List<String>>> tmpMap = new ConcurrentHashMap<String, Map<String,List<String>>>();
//			List<String> subList = zooKeeper.getChildren(path, watcher);
//			//排序 subList
//			Collections.sort(subList);
//			for (String subNode : subList) {
//				if(subNode.equals(DIR_GRAY_LEVEL)) {
//					continue;
//				}
//				/**
//				 * 服务端url获取方式改为直接从结点名称上获取，减少zk的访问次数,并兼容老的结点名称形式
//				 */
//				String invokerUrl = null;
//				if(subNode.indexOf(SERVER_NODE_SPLIT) > 0) {
//					invokerUrl = subNode.replace(SERVER_NODE_SPLIT, "/");
//				}else {
//					byte[] data = zooKeeper.getData(path+"/"+subNode, false,null);
//					invokerUrl = new String(data,Charset.forName("utf-8"));
//				}
//				String[] urls = invokerUrl.split("/");
//				String url = urls[0].trim();
//				String projectName = urls[1].trim();
////				invokerUrl.substring(invokerUrl.lastIndexOf("/")+1, invokerUrl.length()).trim();
//				Map<String,List<String>> urlListMap = tmpMap.get(projectName);
//				if(urlListMap == null){
//					urlListMap = new HashMap<String, List<String>>();
//					tmpMap.put(projectName, urlListMap);
//				}
//				String idc = GlobalMessage.getIDCLocal(url);
//				List<String> idcUrlList = urlListMap.get(idc);
//				if(idcUrlList == null){
//					idcUrlList =new ArrayList<String>();
//					urlListMap.put(idc, idcUrlList);
//				}
//				idcUrlList.add(invokerUrl);
//			}
//
//			invokerUrlMap = tmpMap;
//		}catch (KeeperException e) {
//			try {
//				Thread.sleep(SLEEP_TIME);
//				reloadZkInstance();
//			} catch (InterruptedException e1) {
//				LOGGER.error("",e1);
//			}
//		}catch(Exception e){
//			LOGGER.error("",e);
//			throw new FrameworkRuntimeException("获取子目录节点异常!");
//		}
//
//	}
//
//
//	private synchronized void reloadZkInstance(){
//		if(!ZkLogOutCenter.gracefulClose){
//			this.zooKeeper = manager.getZooKeeper();
//			this.register(StartRegisterCenter.getLocalUrl(),false);
//		}
//	}
//
//
//	public synchronized void close() {
//	    try {
//	    	zooKeeper.close();
//	    } catch (InterruptedException e) {
//	    	LOGGER.error("",e);
//	     }
//	 }
//	/**
//	 * 根据工程名获取可用的服务器调用列表<br/> Created on 2013-9-22 上午10:14:17
//	 * @author  litao(litao02@zhuche.com)
//	 * @since 3.2
//	 * @param projectName
//	 * @return
//	 */
//	public List<String> getServers(String projectName) {
//		long start = System.currentTimeMillis();
//		Assert.notNull(projectName,"工程名不能为空!");
//		Map<String,List<String>> ipListMap = invokerUrlMap.get(projectName);
//		LOG.info("====projectName:"+projectName);
//		if(ipListMap == null || ipListMap.size() ==0){
//
//			Long updateGetZkUrlTime = updateGetZkUrlTimeMap.get(projectName);
//			if(updateGetZkUrlTime == null){
//				updateGetZkUrlTime = 0l;
//			}
//
//			if(System.currentTimeMillis() > updateGetZkUrlTime + 5000){
//
//				Long t1 = System.currentTimeMillis();
//
//				ipListMap = getUrlFromZK(projectName, CLUSTER_PATH);
//
//				GetRemoteServerLog.putLog("从zk获取服务器列表耗时", System.currentTimeMillis() - t1);
//			}else{
//				return null ;
//			}
//
//			if(ipListMap.size() == 0){
//				updateGetZkUrlTimeMap.put(projectName, System.currentTimeMillis());
//			}
//
//		}
//		long  duration= System.currentTimeMillis() -start;
//		if(duration > 1) {
//			Zeus.logEvent("GetRemoteServerListMore1ms",projectName,ZeusTransaction.SUCCESS,String.valueOf(duration));
//		}
//		List<String> list = ipListMap.get(GlobalMessage.getIdc());
//
//		//本机房优先
//		if(list != null) {
//			return list;
//		}
//
//		//任何机房无可用机器
//		if(ipListMap.size() == 0 || ipListMap.containsKey(GlobalMessage.getIdc())) {
//			return null;
//		}
//
//		//跨机房调用
//		for (Entry<String, List<String>> entry : ipListMap.entrySet()) {
//			if(entry == null || entry.getValue() == null) {
//				continue;
//			}
//			return entry.getValue();
//		}
//
//		return list;
//	}
//
//	private synchronized Map<String,List<String>> getUrlFromZK(String projectName ,String path){
//
//		try {
//			Map<String,List<String>> ipListMap = invokerUrlMap.get(projectName);
//
//			if(ipListMap != null && ipListMap.size()>0){
//				return ipListMap;
//			}else{
//				ipListMap = new HashMap<String, List<String>>();
//			}
//
//			List<String> subList = zooKeeper.getChildren(path, watcher);
//			//排序 subList
//			Collections.sort(subList);
//
//			for (String subNode : subList) {
//				/**
//				 * 如果为灰度结点，则忽略
//				 */
//				if(subNode.equals(DIR_GRAY_LEVEL)) {
//					continue;
//				}
//
//				/**
//				 * 获取服务端url方式改为由结点名称转化，减少zk的访问次数，并兼容老的结点名称形式
//				 */
//				String invokerUrl = null;
//				if(subNode.indexOf(SERVER_NODE_SPLIT) > 0) {
//					invokerUrl = subNode.replace(SERVER_NODE_SPLIT, "/");
//				}else {
//					byte[] data = zooKeeper.getData(path+"/"+subNode, false,null);
//					invokerUrl = new String(data,Charset.forName("utf-8"));
//				}
//				String[] urls = invokerUrl.split("/");
//				String url = urls[0].trim();
//				String projectNameZK = urls[1].trim();
////				String projectNameZK = invokerUrl.substring(invokerUrl.lastIndexOf("/")+1, invokerUrl.length()).trim();
//				if(projectName.equals(projectNameZK)){
//					String idc = GlobalMessage.getIDCLocal(url);
//					List<String> resultList = ipListMap.get(idc);
//					if(resultList == null) {
//						resultList = new ArrayList<String>();
//						ipListMap.put(idc, resultList);
//					}
//					resultList.add(invokerUrl);
//				}
//
//			}
//			return ipListMap;
//		}catch (KeeperException e) {
//			try {
//				Thread.sleep(SLEEP_TIME);
//				this.zooKeeper = manager.getZooKeeper();
//				return this.getUrlFromZK(projectName, path);
//			} catch (InterruptedException e1) {
//				LOGGER.error("",e1);
//				return null;
//			}
//		} catch (Exception e) {
//			LOGGER.error("",e);
//			throw new FrameworkRuntimeException("获取子目录节点异常!");
//		}
//	}
//
//	static class ZkRegistryCenterConnectionListener implements ConnectionListener{
//
//		private ZkRegistryCenter zkRegistryCenter ;
//
//		public ZkRegistryCenterConnectionListener(ZkRegistryCenter zkRegistryCenter){
//			this.zkRegistryCenter = zkRegistryCenter ;
//		}
//
//		@Override
//		public void syncConnected() {
//
//
//		}
//
//		@Override
//		public void expired() {
//
//			zkRegistryCenter.reloadZkInstance();
//		}
//
//		@Override
//		public void disconnected() {
//			zkRegistryCenter.reloadZkInstance();
//
//		}
//
//	}
//
//	public Map<String, Map<String,List<String>>> getProject2IPListMap(){
//		Map<String, Map<String,List<String>>> map = new HashMap<String, Map<String,List<String>>>(invokerUrlMap);
//		return map;
//	}
//
//	/**
//	 * 系统启动的时候像zk注册framework版本信息
//	 * @param contextPath
//	 * @param localIp
//	 */
//	public void registerVersion(String contextPath,String localIp) {
//        try {
//        	boolean result = ZkUtils.notExitCreate(manager, ZkUtils.MAVEN_VERSION_PATH);
//        	if(result){
//        		String version = MavenVersionUtils.FRAMEWORK_VERSION;
//				String path = ZkUtils.MAVEN_VERSION_PATH + contextPath + "_" + localIp + "_" + version;
//        		zooKeeper.create(path,version.getBytes("utf-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        		return;
//        	}
//        	LOG.error("register version failed,contextPath="+contextPath+",localIp="+localIp);
//        } catch (Exception e) {
//        	LOG.error("registerVersion occur error,contextPath="+contextPath+",localIp="+localIp, e);
//        }
//    }
//
////	private static final DataChangeListener dataChangeListener = new DataChangeListener() {
////
////        @Override
////        public void call(DataSourceTransport dataSourceTransport) {
////            ClientDataSource clientDataSource = dataSourceTransport.getClientDataSource();
////            if (clientDataSource == null) {
////                LOG.error("client datasource is null ", new NullPointerException());
////                return;
////            }
////            String sourceName = clientDataSource.getSourceName();
////            if (!StringUtils.equals(sourceName, MQ_SWITCH_NAME)) {
////                return;
////            }
////            String batchId = clientDataSource.getBatchId();
////            if (StringUtils.isBlank(batchId)) {
////                LOG.error("batchId is blank", new IllegalStateException());
////                return;
////            }
////            String sourceValue = clientDataSource.getSourceValue();
////            if (StringUtils.isBlank(sourceValue)) {
////                LOG.error("sourceValue is blank ", new IllegalStateException());
////                return;
////            }
////            DcsSwitchInfo dcsSwitchInfo;
////            try {
////                dcsSwitchInfo = JSONUtils.deserializeObject(sourceValue, DcsSwitchInfo.class);
////            } catch (Exception e) {
////                LOG.error("json parse error", e);
////                uploadSwitchResult(dataSourceTransport, false, "dcsSwitchInfo parse error, " + ExceptionUtils.getMessage(e));
////                return;
////            }
////
////            if (!ArrayUtils.contains(StringUtils.split(dcsSwitchInfo.getBusinessLine(), ','), clientDataSource.getBusinessLine())) {
////                LOG.error("BusinessLine not match, so don`t need execute.");
////                uploadSwitchResult(dataSourceTransport, false, "BusinessLine not match");
////                return;
////            }
////
////            String newVersion = dcsSwitchInfo.getVersion();
////            if (StringUtils.isBlank(newVersion)) {
////                LOG.error("version is blank ", new IllegalStateException());
////                return;
////            }
////            /**
////             *	以下主要目的：
////             *			1. 防止重复版本号
////             *			2. 防止误发更小的版本号
////             *			3. 保证幂等
////             *	以下主要逻辑：
////             *			1. 先保证版本号不降低
////             *			2. 获取当前是否有正在执行的version（在执行完，会设置当前运行的版本号为空）
////             *					runningVersion有值 -> 说明有在执行的version，不继续执行
////             *					runningVersion无值 -> 说明可以进行回调对应的操作，最后并设置runningVersion为null
////             */
////            // 保证版本号不降低
////            String oldVersion;
////            do {
////                oldVersion = MetaqConsumerManager.oldVersion.get();
////                if (ObjectUtils.compare(oldVersion, newVersion, false) > 0) {
////                    uploadSwitchResult(dataSourceTransport, false, "wrong version, new version is smaller than old version");
////                    return;
////                }
////            } while (!MetaqConsumerManager.oldVersion.compareAndSet(oldVersion, newVersion));
////
////            String runningVersion = versionMap.putIfAbsent(RUNNING_VERSION, newVersion);
////            if (runningVersion != null) {
////                if (StringUtils.equals(newVersion, runningVersion)) {
////                    LOG.error("重复通知：{}，忽略", sourceValue);
////                } else {
////                    LOG.error("另一个version {} 正在执行中，拒绝执行 {}！", runningVersion, sourceValue);
////                    uploadSwitchResult(dataSourceTransport, false, "another version " + runningVersion + "is executing, so version " + sourceValue + "is rejected");
////                }
////                return;
////            }
////            try {
////                // get current businessLines
////                processConifgData(dcsSwitchInfo);
////                uploadSwitchResult(dataSourceTransport, true, "process switch succeed");
////            } catch (InterruptedException e) {
////                Thread.currentThread().interrupt();
////                LOG.error("", e);
////                uploadSwitchResult(dataSourceTransport, false, "process switch interrupted, "+ ExceptionUtils.getMessage(e));
////                return;
////            } finally {
////                versionMap.remove(RUNNING_VERSION);
////            }
////
////        }
////    };
//
//	/**
//	 * 获取版本信息
//	 * @return
//	 *//*
//	public List<String> getVersions(){
//		try {
//			if (zooKeeper.exists(ZkUtils.MAVEN_VERSION_PATH, false) == null) {
//				return null;
//			}
//			List<String> versions = zooKeeper.getChildren(ZkUtils.MAVEN_VERSION_PATH, null);
//			return versions;
//		} catch (KeeperException e) {
//			LOG.error("getVersions failed:",e);
//		} catch (InterruptedException e) {
//			LOG.error("getVersions failed :",e);
//		}
//		return null;
//	}*/
//}

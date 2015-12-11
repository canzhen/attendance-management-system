package action;

import java.util.Map;

public class teacherAction extends MyActionSupport{
	private Map request;
	/**
	 * 鍦ㄨ繑鍥為〉闈箣鍓嶏紝闇�瑕佷粠鏁版嵁搴撲腑姣斿锛屾煡鎵惧綋鍓嶈涓婄殑璇撅紝
	 * 鐒跺悗鎶婅绋嬩俊鎭斁鍒皉equest閲屼紶閫掕繃鍘汇��
	 * 鑻ュ綋鍓嶆椂闂存娌℃湁璇撅紝閭ｄ箞灏辫繑鍥濶OCURRENTCLASS锛岃烦杞埌鏃犺椤甸潰銆�
	 */

	@Override
	public String index(){
		request = getRequest();
		request.put("test", new Integer(1));//绫讳技杩欐牱浼犲�艰繃鍘�
		return SUCCESS;
	}
}

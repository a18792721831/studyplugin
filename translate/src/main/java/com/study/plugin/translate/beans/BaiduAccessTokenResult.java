package com.study.plugin.translate.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author jiayq
 * @Date 2024-12-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * {
 *   "refresh_token" : "25.1f94e9298262338e5ee89cb76c4bc382.315360000.2051087724.282335-116916729",
 *   "expires_in" : 2592000,
 *   "session_key" : "9mzdC3mXcyKeVaih8IH1pBBEhn/Hy0TDHR947p8Q0OVKm5biY4hGA1rgZ3dNUvwJ/XanHO9faawvzLevBsF81Op0cxautHU=",
 *   "access_token" : "24.0aa639d57ca34cddbbd9b020c65e6771.2592000.1738319724.282335-116916729",
 *   "scope" : "public ai_custom_qianfan_bloomz_7b_compressed ai_custom_yiyan_com ai_custom_yiyan_com_128k ai_custom_yiyan_com_ai_apaas ai_custom_yiyan_com_ai_apaas_lite ai_custom_yiyan_com_aquilachat_7b ai_custom_yiyan_com_bce_reranker_base ai_custom_yiyan_com_bloomz7b1 ai_custom_yiyan_com_chatglm2_6b_32k ai_custom_yiyan_com_chatlaw ai_custom_yiyan_com_codellama_7b_ins ai_custom_yiyan_com_eb_pro ai_custom_yiyan_com_eb_turbo_pro ai_custom_yiyan_com_eb_turbo_pro_128k ai_custom_yiyan_com_emb_bge_large_en ai_custom_yiyan_com_emb_bge_large_zh ai_custom_yiyan_com_emb_tao_8k ai_custom_yiyan_com_emb_text ai_custom_yiyan_com_ernie_3.5_128k_preview ai_custom_yiyan_com_ernie_3.5_8k_0701 ai_custom_yiyan_com_ernie_35_8k_0613 ai_custom_yiyan_com_ernie_35_8k_preview ai_custom_yiyan_com_ernie_4.0_turbo_128k ai_custom_yiyan_com_ernie_4.0_turbo_8k_0628 ai_custom_yiyan_com_ernie_4.0_turbo_8k_latest ai_custom_yiyan_com_ernie_40_8k_0613 ai_custom_yiyan_com_ernie_40_8k_beta ai_custom_yiyan_com_ernie_40_8k_preview ai_custom_yiyan_com_ernie_40_turbo_8k(2) ai_custom_yiyan_com_ernie_40_turbo_8k_preview ai_custom_yiyan_com_ernie_char_8k ai_custom_yiyan_com_ernie_char_fiction_8k ai_custom_yiyan_com_ernie_func_8k ai_custom_yiyan_com_ernie_lite_8k ai_custom_yiyan_com_ernie_lite_pro_128k ai_custom_yiyan_com_ernie_novel_8k ai_custom_yiyan_com_ernie_speed_pro_128k ai_custom_yiyan_com_ernie_tiny_8k ai_custom_yiyan_com_fiction_8k_preview ai_custom_yiyan_com_fuyu_8b ai_custom_yiyan_com_gemma_7b_it ai_custom_yiyan_com_llama2_13b ai_custom_yiyan_com_llama2_70b ai_custom_yiyan_com_llama2_7b ai_custom_yiyan_com_llama3_70b ai_custom_yiyan_com_llama3_8b ai_custom_yiyan_com_mixtral_8x7b ai_custom_yiyan_com_qf_chinese_llama_2_13b ai_custom_yiyan_com_qf_chinese_llama_2_70b ai_custom_yiyan_com_qianfan_agent_lite_8k ai_custom_yiyan_com_qianfan_agent_speed_8k ai_custom_yiyan_com_qianfan_chinese_llama_2_7b ai_custom_yiyan_com_qianfan_dynamic_8k ai_custom_yiyan_com_sd_xl ai_custom_yiyan_com_sqlcoder_7b ai_custom_yiyan_com_tokenizer_eb ai_custom_yiyan_com_xuanyuan_70b_chat ai_custom_yiyan_com_yi_34b brain_all_scope wenxinworkshop_mgr wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base smartapp_mapp_dev_manage iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi smartapp_opensource_recapi fake_face_detect_开放Scope vis-ocr_虚拟人物助理 idl-video_虚拟人物助理 smartapp_component smartapp_search_plugin avatar_video_test b2b_tp_openapi b2b_tp_openapi_online smartapp_gov_aladin_to_xcx",
 *   "session_secret" : "f1fe979b847a7af15e780113b2e458c0"
 * }
 */
public class BaiduAccessTokenResult implements Serializable {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("error")
    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("session_secret")
    private String sessionSecret;
}

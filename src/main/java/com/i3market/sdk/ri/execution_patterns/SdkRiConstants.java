package com.i3market.sdk.ri.execution_patterns;

// SDK RI Constans
public final class SdkRiConstants {
	
	// SdkRiConfiguration file path
	public static final String PATH_CONFFILE = "sdk_ri_config.properties";
	
	// BackplaneAPI public endpoint (URL)
	public static final String BACKPLANE_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.BACKPLANE_URL);
	
	// OIDC API public endpoint (URL)
	public static final String OIDC_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.OIDC_URL);
		
	// Verifiable Credentials API public endpoint (URL)
	public static final String VC_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.VC_URL);
	
	// Data Access API public endpoint (URL)
	public static final String DATA_ACCESS_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.DATA_ACCESS_URL);
	
	// SW Wallet API public endpoint (URL)
	public static final String SW_WALLET_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.SW_WALLET_URL);

}

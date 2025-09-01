package com.zifo.ewb.alliance.serviceimplementation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public interface IAllianceCatalogServiceImplementation {
	
	public void updateAllianceDettail() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, URISyntaxException;
}
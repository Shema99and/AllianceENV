package com.zifo.ewb.alliance.webserviceutils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Utility class to help with marshalling or unmarshalling of SAPI Types
 */
public final class MarshallingHelper {

	private MarshallingHelper() {

	}

	/**
	 * Marshalls a SAPI Type
	 * 
	 * @param <T>
	 *            SAPI Type (to marshall)
	 * @param jaxbClass
	 *            the SAPI class reference
	 * @param elements
	 *            SAPI class detail
	 * @return The marshalled object as a string
	 * @throws javax.xml.bind.JAXBException
	 */
	public static <T> String marshallJaxb(final Class<T> jaxbClass, final JAXBElement<?> elements) throws JAXBException {
		final JAXBContext jaxbContext = getContext(jaxbClass);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		final OutputStream outputStream = new ByteArrayOutputStream();
		marshaller.marshal(elements, outputStream);
		return outputStream.toString();
	}

	/**
	 * Unmarshalls a HHTP response into a SAPI Type
	 * 
	 * @param <T>
	 *            SAPI Type (to unmarshall)
	 * @param jaxbClass
	 *            the SAPI class reference
	 * @param body
	 *            the response body
	 * @return unmarshalled SAPI Type
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshallJaxb(final Class<T> jaxbClass, final String body) throws JAXBException {
		final JAXBContext jaxbContext = getContext(jaxbClass);
		final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Object object;
		try (StringReader stringReader = new StringReader(body)) {
			object = unmarshaller.unmarshal(stringReader);
		}

		T unmarshall;
		if (object instanceof JAXBElement<?>) {
			unmarshall = (T) ((JAXBElement<?>) object).getValue();
		} else {
			unmarshall = (T) object;
		}
		return unmarshall;
	}

	/**
	 * Provides the client's entry point to the JAXB API
	 * 
	 * @param jaxbClass
	 *            the SAPI Type class reference
	 * @return JAXBContext for processing the SAPI Type
	 * @throws JAXBException
	 */
	private static JAXBContext getContext(final Class<?> jaxbClass) throws JAXBException {
		JAXBContext jaxbContext;

		try {
			jaxbContext = JAXBContext.newInstance(jaxbClass.getPackage().getName(), jaxbClass.getClassLoader());
		} catch (JAXBException ex) {
			jaxbContext = JAXBContext.newInstance(jaxbClass);
		}
		return jaxbContext;

	}

}
package by.bsuir.plugin.loaders;

/**
 * Created by Koshelek on 11.04.2017.
 */
/*
 * @(#)MyJCE.java	1.4 02/01/17
 *
 * Copyright (c) 2002, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * -Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduct the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of Oracle or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or
 * intended for use in the design, construction, operation or
  * maintenance of any nuclear facility.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public final class JarVerifier {

    // Provider's signing cert which is used to sign the jar.
    private X509Certificate providerCert = null;

    public JarVerifier() throws IOException,CertificateException  {
        providerCert = setupCert();
    }

    // Raw bytes of provider's own code signing cert.
    // NOTE: YOU NEED TO CHANGE THIS TO YOUR OWN PROVIDER CERTIFICATE
    private final byte[] bytesOfProviderCert = {
            (byte) 0x30, (byte) 0x82, (byte) 0x03, (byte) 0x6F, (byte) 0x30, (byte) 0x82, (byte) 0x02,
            (byte) 0x57, (byte) 0xA0, (byte) 0x03, (byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x02,
            (byte) 0x04, (byte) 0x73, (byte) 0x47, (byte) 0xE9, (byte) 0x95, (byte) 0x30, (byte) 0x0D,
            (byte) 0x06, (byte) 0x09, (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7,
            (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x0B, (byte) 0x05, (byte) 0x00, (byte) 0x30,
            (byte) 0x68, (byte) 0x31, (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03,
            (byte) 0x55, (byte) 0x04, (byte) 0x06, (byte) 0x13, (byte) 0x02, (byte) 0x42, (byte) 0x59,
            (byte) 0x31, (byte) 0x10, (byte) 0x30, (byte) 0x0E, (byte) 0x06, (byte) 0x03, (byte) 0x55,
            (byte) 0x04, (byte) 0x08, (byte) 0x13, (byte) 0x07, (byte) 0x42, (byte) 0x65, (byte) 0x6C,
            (byte) 0x61, (byte) 0x72, (byte) 0x75, (byte) 0x73, (byte) 0x31, (byte) 0x0E, (byte) 0x30,
            (byte) 0x0C, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x07, (byte) 0x13,
            (byte) 0x05, (byte) 0x4D, (byte) 0x69, (byte) 0x6E, (byte) 0x73, (byte) 0x6B, (byte) 0x31,
            (byte) 0x0E, (byte) 0x30, (byte) 0x0C, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04,
            (byte) 0x0A, (byte) 0x13, (byte) 0x05, (byte) 0x42, (byte) 0x53, (byte) 0x55, (byte) 0x49,
            (byte) 0x52, (byte) 0x31, (byte) 0x0D, (byte) 0x30, (byte) 0x0B, (byte) 0x06, (byte) 0x03,
            (byte) 0x55, (byte) 0x04, (byte) 0x0B, (byte) 0x13, (byte) 0x04, (byte) 0x4B, (byte) 0x53,
            (byte) 0x69, (byte) 0x53, (byte) 0x31, (byte) 0x18, (byte) 0x30, (byte) 0x16, (byte) 0x06,
            (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x03, (byte) 0x13, (byte) 0x0F, (byte) 0x44,
            (byte) 0x6D, (byte) 0x69, (byte) 0x74, (byte) 0x72, (byte) 0x79, (byte) 0x20, (byte) 0x4B,
            (byte) 0x6F, (byte) 0x73, (byte) 0x68, (byte) 0x65, (byte) 0x6C, (byte) 0x65, (byte) 0x76,
            (byte) 0x30, (byte) 0x1E, (byte) 0x17, (byte) 0x0D, (byte) 0x31, (byte) 0x37, (byte) 0x30,
            (byte) 0x34, (byte) 0x31, (byte) 0x31, (byte) 0x30, (byte) 0x38, (byte) 0x34, (byte) 0x33,
            (byte) 0x35, (byte) 0x35, (byte) 0x5A, (byte) 0x17, (byte) 0x0D, (byte) 0x31, (byte) 0x37,
            (byte) 0x30, (byte) 0x37, (byte) 0x31, (byte) 0x30, (byte) 0x30, (byte) 0x38, (byte) 0x34,
            (byte) 0x33, (byte) 0x35, (byte) 0x35, (byte) 0x5A, (byte) 0x30, (byte) 0x68, (byte) 0x31,
            (byte) 0x0B, (byte) 0x30, (byte) 0x09, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04,
            (byte) 0x06, (byte) 0x13, (byte) 0x02, (byte) 0x42, (byte) 0x59, (byte) 0x31, (byte) 0x10,
            (byte) 0x30, (byte) 0x0E, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x08,
            (byte) 0x13, (byte) 0x07, (byte) 0x42, (byte) 0x65, (byte) 0x6C, (byte) 0x61, (byte) 0x72,
            (byte) 0x75, (byte) 0x73, (byte) 0x31, (byte) 0x0E, (byte) 0x30, (byte) 0x0C, (byte) 0x06,
            (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x07, (byte) 0x13, (byte) 0x05, (byte) 0x4D,
            (byte) 0x69, (byte) 0x6E, (byte) 0x73, (byte) 0x6B, (byte) 0x31, (byte) 0x0E, (byte) 0x30,
            (byte) 0x0C, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04, (byte) 0x0A, (byte) 0x13,
            (byte) 0x05, (byte) 0x42, (byte) 0x53, (byte) 0x55, (byte) 0x49, (byte) 0x52, (byte) 0x31,
            (byte) 0x0D, (byte) 0x30, (byte) 0x0B, (byte) 0x06, (byte) 0x03, (byte) 0x55, (byte) 0x04,
            (byte) 0x0B, (byte) 0x13, (byte) 0x04, (byte) 0x4B, (byte) 0x53, (byte) 0x69, (byte) 0x53,
            (byte) 0x31, (byte) 0x18, (byte) 0x30, (byte) 0x16, (byte) 0x06, (byte) 0x03, (byte) 0x55,
            (byte) 0x04, (byte) 0x03, (byte) 0x13, (byte) 0x0F, (byte) 0x44, (byte) 0x6D, (byte) 0x69,
            (byte) 0x74, (byte) 0x72, (byte) 0x79, (byte) 0x20, (byte) 0x4B, (byte) 0x6F, (byte) 0x73,
            (byte) 0x68, (byte) 0x65, (byte) 0x6C, (byte) 0x65, (byte) 0x76, (byte) 0x30, (byte) 0x82,
            (byte) 0x01, (byte) 0x22, (byte) 0x30, (byte) 0x0D, (byte) 0x06, (byte) 0x09, (byte) 0x2A,
            (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7, (byte) 0x0D, (byte) 0x01, (byte) 0x01,
            (byte) 0x01, (byte) 0x05, (byte) 0x00, (byte) 0x03, (byte) 0x82, (byte) 0x01, (byte) 0x0F,
            (byte) 0x00, (byte) 0x30, (byte) 0x82, (byte) 0x01, (byte) 0x0A, (byte) 0x02, (byte) 0x82,
            (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0xC1, (byte) 0x34, (byte) 0x38, (byte) 0x3F,
            (byte) 0x6E, (byte) 0xE5, (byte) 0x7C, (byte) 0x81, (byte) 0x6A, (byte) 0x78, (byte) 0x1E,
            (byte) 0x8B, (byte) 0x41, (byte) 0x16, (byte) 0xBD, (byte) 0xB2, (byte) 0x01, (byte) 0xE5,
            (byte) 0x32, (byte) 0x10, (byte) 0x3D, (byte) 0xD5, (byte) 0xA6, (byte) 0x8D, (byte) 0x4C,
            (byte) 0xE6, (byte) 0xA9, (byte) 0x6F, (byte) 0x8B, (byte) 0x44, (byte) 0x0E, (byte) 0x00,
            (byte) 0x59, (byte) 0xAF, (byte) 0x28, (byte) 0x94, (byte) 0x9F, (byte) 0x52, (byte) 0x1F,
            (byte) 0x19, (byte) 0xEB, (byte) 0x32, (byte) 0x7C, (byte) 0x2B, (byte) 0xCB, (byte) 0xB6,
            (byte) 0xCF, (byte) 0x97, (byte) 0x55, (byte) 0x2A, (byte) 0xE8, (byte) 0x15, (byte) 0x3E,
            (byte) 0x5D, (byte) 0xD2, (byte) 0x07, (byte) 0x61, (byte) 0xF6, (byte) 0x5E, (byte) 0xAF,
            (byte) 0x65, (byte) 0x09, (byte) 0x1D, (byte) 0xE0, (byte) 0xE4, (byte) 0x8B, (byte) 0x62,
            (byte) 0x77, (byte) 0xD6, (byte) 0xE9, (byte) 0xED, (byte) 0x14, (byte) 0x06, (byte) 0x18,
            (byte) 0x88, (byte) 0x31, (byte) 0xDE, (byte) 0x56, (byte) 0x13, (byte) 0xB0, (byte) 0xEB,
            (byte) 0x09, (byte) 0x25, (byte) 0x3D, (byte) 0xF6, (byte) 0x78, (byte) 0x39, (byte) 0x00,
            (byte) 0xDE, (byte) 0x94, (byte) 0xD9, (byte) 0x52, (byte) 0xED, (byte) 0x45, (byte) 0xA6,
            (byte) 0xFB, (byte) 0x2F, (byte) 0xEB, (byte) 0x3F, (byte) 0x03, (byte) 0x91, (byte) 0x2F,
            (byte) 0x12, (byte) 0xE2, (byte) 0x31, (byte) 0x41, (byte) 0x3D, (byte) 0x52, (byte) 0x49,
            (byte) 0x8D, (byte) 0x21, (byte) 0x34, (byte) 0xE1, (byte) 0xD1, (byte) 0xD2, (byte) 0xD3,
            (byte) 0x2A, (byte) 0xE5, (byte) 0x7A, (byte) 0x6F, (byte) 0x1A, (byte) 0x53, (byte) 0x1B,
            (byte) 0xA4, (byte) 0x9F, (byte) 0x5A, (byte) 0xF9, (byte) 0xEA, (byte) 0xBF, (byte) 0xCB,
            (byte) 0x37, (byte) 0xF7, (byte) 0x79, (byte) 0xBA, (byte) 0x2E, (byte) 0x8D, (byte) 0xD2,
            (byte) 0x75, (byte) 0xBD, (byte) 0x99, (byte) 0xFB, (byte) 0x9E, (byte) 0x00, (byte) 0x45,
            (byte) 0x4F, (byte) 0xB0, (byte) 0x04, (byte) 0x8B, (byte) 0x80, (byte) 0xF7, (byte) 0xD1,
            (byte) 0xA9, (byte) 0x76, (byte) 0xA8, (byte) 0x88, (byte) 0x05, (byte) 0xDB, (byte) 0x5B,
            (byte) 0x9E, (byte) 0x6F, (byte) 0x59, (byte) 0x9D, (byte) 0x5D, (byte) 0x84, (byte) 0xA7,
            (byte) 0x9F, (byte) 0xD4, (byte) 0xA8, (byte) 0xB7, (byte) 0xA9, (byte) 0x3A, (byte) 0x8F,
            (byte) 0xAF, (byte) 0xF9, (byte) 0x75, (byte) 0x88, (byte) 0x37, (byte) 0x9C, (byte) 0xA2,
            (byte) 0xA7, (byte) 0x6D, (byte) 0x70, (byte) 0x21, (byte) 0xC1, (byte) 0x0F, (byte) 0x99,
            (byte) 0x63, (byte) 0xFB, (byte) 0x78, (byte) 0x09, (byte) 0x49, (byte) 0x97, (byte) 0xEF,
            (byte) 0x9B, (byte) 0x8A, (byte) 0x67, (byte) 0xAD, (byte) 0xF8, (byte) 0xA3, (byte) 0xF7,
            (byte) 0x64, (byte) 0xEC, (byte) 0x14, (byte) 0x6C, (byte) 0xEF, (byte) 0x56, (byte) 0xED,
            (byte) 0x1B, (byte) 0x4E, (byte) 0xF1, (byte) 0xC7, (byte) 0x20, (byte) 0xD3, (byte) 0x14,
            (byte) 0x6F, (byte) 0xAC, (byte) 0x4F, (byte) 0xA6, (byte) 0x05, (byte) 0xA1, (byte) 0x13,
            (byte) 0x15, (byte) 0x05, (byte) 0x8D, (byte) 0x3D, (byte) 0x06, (byte) 0xCB, (byte) 0xE2,
            (byte) 0x8F, (byte) 0x01, (byte) 0x53, (byte) 0x04, (byte) 0x58, (byte) 0x08, (byte) 0x73,
            (byte) 0xB4, (byte) 0x2E, (byte) 0xED, (byte) 0x2F, (byte) 0x39, (byte) 0xE3, (byte) 0x7C,
            (byte) 0xB3, (byte) 0xFA, (byte) 0x9E, (byte) 0xBE, (byte) 0x88, (byte) 0xF2, (byte) 0xDA,
            (byte) 0x22, (byte) 0xEC, (byte) 0x21, (byte) 0x76, (byte) 0x40, (byte) 0x10, (byte) 0x4F,
            (byte) 0x02, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0xA3, (byte) 0x21,
            (byte) 0x30, (byte) 0x1F, (byte) 0x30, (byte) 0x1D, (byte) 0x06, (byte) 0x03, (byte) 0x55,
            (byte) 0x1D, (byte) 0x0E, (byte) 0x04, (byte) 0x16, (byte) 0x04, (byte) 0x14, (byte) 0x63,
            (byte) 0x11, (byte) 0xC0, (byte) 0x1C, (byte) 0x0E, (byte) 0xAE, (byte) 0x34, (byte) 0x28,
            (byte) 0xFD, (byte) 0x47, (byte) 0xA0, (byte) 0x53, (byte) 0x69, (byte) 0xE7, (byte) 0xF3,
            (byte) 0x67, (byte) 0x3E, (byte) 0x7B, (byte) 0x5B, (byte) 0x8E, (byte) 0x30, (byte) 0x0D,
            (byte) 0x06, (byte) 0x09, (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7,
            (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x0B, (byte) 0x05, (byte) 0x00, (byte) 0x03,
            (byte) 0x82, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x1A, (byte) 0x09, (byte) 0x56,
            (byte) 0x3F, (byte) 0xA0, (byte) 0xDD, (byte) 0x89, (byte) 0x67, (byte) 0xC2, (byte) 0x05,
            (byte) 0x85, (byte) 0x0E, (byte) 0x3D, (byte) 0x57, (byte) 0x4C, (byte) 0xC9, (byte) 0x11,
            (byte) 0x3F, (byte) 0x8C, (byte) 0x20, (byte) 0xBD, (byte) 0x83, (byte) 0x1F, (byte) 0x95,
            (byte) 0x36, (byte) 0x49, (byte) 0x36, (byte) 0x09, (byte) 0x7E, (byte) 0x77, (byte) 0xEB,
            (byte) 0x20, (byte) 0x8B, (byte) 0x04, (byte) 0xBD, (byte) 0x09, (byte) 0x15, (byte) 0x4A,
            (byte) 0x46, (byte) 0x29, (byte) 0xED, (byte) 0xFF, (byte) 0xDB, (byte) 0xE5, (byte) 0xCC,
            (byte) 0x29, (byte) 0x10, (byte) 0xD3, (byte) 0x58, (byte) 0x20, (byte) 0xE3, (byte) 0xAE,
            (byte) 0x80, (byte) 0xB1, (byte) 0x6A, (byte) 0xE4, (byte) 0xA9, (byte) 0x3E, (byte) 0xC7,
            (byte) 0x20, (byte) 0xD1, (byte) 0x95, (byte) 0x75, (byte) 0x02, (byte) 0xC5, (byte) 0x7B,
            (byte) 0x44, (byte) 0x5A, (byte) 0x76, (byte) 0xEF, (byte) 0xF8, (byte) 0x77, (byte) 0xCD,
            (byte) 0xFB, (byte) 0xAF, (byte) 0x11, (byte) 0x9D, (byte) 0x22, (byte) 0xBE, (byte) 0xEE,
            (byte) 0x50, (byte) 0x4D, (byte) 0x4E, (byte) 0x55, (byte) 0xD3, (byte) 0x92, (byte) 0xF5,
            (byte) 0x3E, (byte) 0x8B, (byte) 0x10, (byte) 0x06, (byte) 0x43, (byte) 0x75, (byte) 0xCC,
            (byte) 0xCE, (byte) 0xF0, (byte) 0x31, (byte) 0xC1, (byte) 0x31, (byte) 0xDB, (byte) 0xF8,
            (byte) 0xF2, (byte) 0xBC, (byte) 0x7A, (byte) 0x86, (byte) 0x91, (byte) 0x22, (byte) 0x4A,
            (byte) 0x4F, (byte) 0xA1, (byte) 0xC0, (byte) 0x90, (byte) 0x0B, (byte) 0x2B, (byte) 0xE9,
            (byte) 0xAD, (byte) 0x56, (byte) 0x32, (byte) 0x90, (byte) 0xE9, (byte) 0xC3, (byte) 0x47,
            (byte) 0x82, (byte) 0xEF, (byte) 0xFB, (byte) 0xE5, (byte) 0x5C, (byte) 0xA7, (byte) 0x7C,
            (byte) 0xDE, (byte) 0x0E, (byte) 0xD0, (byte) 0xD9, (byte) 0x81, (byte) 0xFE, (byte) 0x73,
            (byte) 0x81, (byte) 0x45, (byte) 0x39, (byte) 0x10, (byte) 0x73, (byte) 0x2E, (byte) 0x51,
            (byte) 0x5F, (byte) 0xBE, (byte) 0xFC, (byte) 0x08, (byte) 0x5C, (byte) 0x87, (byte) 0x3C,
            (byte) 0xE6, (byte) 0x5F, (byte) 0xEC, (byte) 0x31, (byte) 0x46, (byte) 0x62, (byte) 0x1F,
            (byte) 0x20, (byte) 0x4C, (byte) 0xB5, (byte) 0x08, (byte) 0x21, (byte) 0x36, (byte) 0xFB,
            (byte) 0xC0, (byte) 0x48, (byte) 0xAA, (byte) 0x39, (byte) 0xBB, (byte) 0xCC, (byte) 0xF8,
            (byte) 0x04, (byte) 0xA5, (byte) 0x0E, (byte) 0x6A, (byte) 0x40, (byte) 0x9B, (byte) 0x8A,
            (byte) 0xA4, (byte) 0x6E, (byte) 0xF5, (byte) 0x14, (byte) 0x3A, (byte) 0xD3, (byte) 0xF0,
            (byte) 0xC7, (byte) 0x3B, (byte) 0x16, (byte) 0xE8, (byte) 0xE0, (byte) 0x6F, (byte) 0x56,
            (byte) 0xE4, (byte) 0xE9, (byte) 0x74, (byte) 0x89, (byte) 0x44, (byte) 0xF6, (byte) 0x5B,
            (byte) 0x29, (byte) 0x1D, (byte) 0xD6, (byte) 0xC2, (byte) 0xA6, (byte) 0x08, (byte) 0xF6,
            (byte) 0x2F, (byte) 0x18, (byte) 0x15, (byte) 0xF7, (byte) 0x40, (byte) 0x6E, (byte) 0x11,
            (byte) 0xB3, (byte) 0x99, (byte) 0xED, (byte) 0x81, (byte) 0xF8, (byte) 0x75, (byte) 0xB7,
            (byte) 0x06, (byte) 0x6B, (byte) 0x08, (byte) 0xD6, (byte) 0x77, (byte) 0x7D, (byte) 0xC7,
            (byte) 0xBC, (byte) 0x0E, (byte) 0x59, (byte) 0x2F, (byte) 0xF7, (byte) 0x22, (byte) 0xE9,
            (byte) 0xF4, (byte) 0xD2, (byte) 0xCB, (byte) 0x73, (byte) 0x5D, (byte) 0xF8, (byte) 0xFB,
            (byte) 0x02, (byte) 0x38, (byte) 0x25, (byte) 0x3D, (byte) 0x8C, (byte) 0x20, (byte) 0xA7,
            (byte) 0x27, (byte) 0x83, (byte) 0xFE, (byte) 0x6F, (byte) 0x6A, (byte) 0x0B, (byte) 0x88,
            (byte) 0x29
    };

    private X509Certificate setupCert()
            throws IOException, CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream inStream = new ByteArrayInputStream(
                bytesOfProviderCert);
        X509Certificate cert = (X509Certificate)
                cf.generateCertificate(inStream);
        inStream.close();
        return cert;
    }

    /**
     * First, retrieve the jar file from the URL passed in constructor.
     * Then, compare it to the expected X509Certificate.
     * If everything went well and the certificates are the same, no
     * exception is thrown.
     */
    public void verify(JarFile jarFile) throws IOException {
        X509Certificate targetCert = providerCert;
        // Sanity checking
        if (targetCert == null) {
            throw new SecurityException("Provider certificate is invalid");
        }

        Vector<JarEntry> entriesVec = new Vector<JarEntry>();

        // Ensure the jar file is signed.
        Manifest man = jarFile.getManifest();
        if (man == null) {
            throw new SecurityException("The provider is not signed");
        }

        // Ensure all the entries' signatures verify correctly
        byte[] buffer = new byte[8192];
        Enumeration entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry je = (JarEntry) entries.nextElement();

            // Skip directories.
            if (je.isDirectory()) continue;
            entriesVec.addElement(je);
            InputStream is = jarFile.getInputStream(je);

            // Read in each jar entry. A security exception will
            // be thrown if a signature/digest check fails.
            int n;
            while ((n = is.read(buffer, 0, buffer.length)) != -1) {
                // Don't care
            }
            is.close();
        }

        // Get the list of signer certificates
        Enumeration e = entriesVec.elements();

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();

            // Every file must be signed except files in META-INF.
            Certificate[] certs = je.getCertificates();
            if ((certs == null) || (certs.length == 0)) {
                if (!je.getName().startsWith("META-INF"))
                    throw new SecurityException("The provider " +
                            "has unsigned " +
                            "class files.");
            } else {
                // Check whether the file is signed by the expected
                // signer. The jar may be signed by multiple signers.
                // See if one of the signers is 'targetCert'.
                int startIndex = 0;
                X509Certificate[] certChain;
                boolean signedAsExpected = false;

                while ((certChain = getAChain(certs, startIndex)) != null) {
                    if (certChain[0].equals(targetCert)) {
                        // Stop since one trusted signer is found.
                        signedAsExpected = true;
                        break;
                    }
                    // Proceed to the next chain.
                    startIndex += certChain.length;
                }

                if (!signedAsExpected) {
                    throw new SecurityException("The provider " +
                            "is not signed by a " +
                            "trusted signer");
                }
            }
        }
    }

    private static X509Certificate[] getAChain(Certificate[] certs,
                                               int startIndex) {
        if (startIndex > certs.length - 1)
            return null;

        int i;
        // Keep going until the next certificate is not the
        // issuer of this certificate.
        for (i = startIndex; i < certs.length - 1; i++) {
            if (!((X509Certificate)certs[i + 1]).getSubjectDN().
                    equals(((X509Certificate)certs[i]).getIssuerDN())) {
                break;
            }
        }
        // Construct and return the found certificate chain.
        int certChainSize = (i-startIndex) + 1;
        X509Certificate[] ret = new X509Certificate[certChainSize];
        for (int j = 0; j < certChainSize; j++ ) {
            ret[j] = (X509Certificate) certs[startIndex + j];
        }
        return ret;
    }

}
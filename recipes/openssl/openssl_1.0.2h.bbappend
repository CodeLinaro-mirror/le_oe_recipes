FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# get patches

python do_getpatches() {
    import os
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/configure-targets.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/configure-targets.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/shared-libs.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/shared-libs.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/oe-ldflags.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/oe-ldflags.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/engines-install-in-libdir-ssl.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/engines-install-in-libdir-ssl.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian1.0.2/block_diginotar.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian1.0.2/block_diginotar.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian1.0.2/block_digicert_malaysia.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian1.0.2/block_digicert_malaysia.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/ca.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/ca.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/c_rehash-compat.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/c_rehash-compat.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/debian-targets.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/debian-targets.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/man-dir.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/man-dir.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/man-section.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/man-section.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/no-rpath.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/no-rpath.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/no-symbolic.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/no-symbolic.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian/pic.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian/pic.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/debian1.0.2/version-script.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/debian1.0.2/version-script.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/openssl_fix_for_x32.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/openssl_fix_for_x32.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/fix-cipher-des-ede3-cfb1.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/fix-cipher-des-ede3-cfb1.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/openssl-avoid-NULL-pointer-dereference-in-EVP_DigestInit_ex.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/openssl-avoid-NULL-pointer-dereference-in-EVP_DigestInit_ex.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/openssl-fix-des.pod-error.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/openssl-fix-des.pod-error.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/Makefiles-ptest.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/Makefiles-ptest.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/ptest-deps.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/ptest-deps.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/crypto_use_bigint_in_x86-64_perl.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/crypto_use_bigint_in_x86-64_perl.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/openssl-1.0.2a-x32-asm.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/openssl-1.0.2a-x32-asm.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/ptest_makefile_deps.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/ptest_makefile_deps.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/parallel.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/parallel.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2177.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2177.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2178.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2178.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2180.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2180.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2181_p1.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2181_p1.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2181_p2.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2181_p2.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2181_p3.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2181_p3.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2182.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2182.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-6302.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-6302.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-6303.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-6303.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-6304.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-6304.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-6306.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-6306.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-2179.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-2179.patch"
    os.system(cmd)
    cmd = "wget http://cgit.openembedded.org/openembedded-core/plain/meta/recipes-connectivity/openssl/openssl/CVE-2016-8610.patch?h=jethro -O ${WORKSPACE}/oe-core/meta-msm/recipes/openssl/openssl/CVE-2016-8610.patch"
    os.system(cmd)
}

addtask getpatches before do_fetch


SRC_URI_append = "\
    file://configure-targets.patch	\
	file://shared-libs.patch	\
	file://oe-ldflags.patch	\
	file://engines-install-in-libdir-ssl.patch	\
	file://debian1.0.2/block_diginotar.patch	\
	file://debian1.0.2/block_digicert_malaysia.patch	\
	file://debian/ca.patch	\
	file://debian/c_rehash-compat.patch	\
	file://debian/debian-targets.patch	\
	file://debian/man-dir.patch	\
	file://debian/man-section.patch	\
	file://debian/no-rpath.patch	\
	file://debian/no-symbolic.patch	\
	file://debian/pic.patch	\
	file://debian1.0.2/version-script.patch	\
	file://openssl_fix_for_x32.patch	\
	file://fix-cipher-des-ede3-cfb1.patch	\
	file://openssl-avoid-NULL-pointer-dereference-in-EVP_DigestInit_ex.patch	\
	file://openssl-fix-des.pod-error.patch	\
	file://Makefiles-ptest.patch	\
	file://ptest-deps.patch	\
	file://crypto_use_bigint_in_x86-64_perl.patch	\
	file://openssl-1.0.2a-x32-asm.patch	\
	file://ptest_makefile_deps.patch	\
	file://parallel.patch	\
	file://CVE-2016-2177.patch	\
	file://CVE-2016-2178.patch	\
	file://CVE-2016-2180.patch	\
	file://CVE-2016-2181_p1.patch	\
	file://CVE-2016-2181_p2.patch	\
	file://CVE-2016-2181_p3.patch	\
	file://CVE-2016-2182.patch	\
	file://CVE-2016-6302.patch	\
	file://CVE-2016-6303.patch	\
	file://CVE-2016-6304.patch	\
	file://CVE-2016-6306.patch	\
	file://CVE-2016-2179.patch	\
	file://CVE-2016-8610.patch	\
"

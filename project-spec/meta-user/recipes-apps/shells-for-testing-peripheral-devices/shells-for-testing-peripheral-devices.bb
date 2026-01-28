#
# This file is the shells-for-testing-peripheral-devices recipe.
#

SUMMARY = "Simple shells-for-testing-peripheral-devices application"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} += "bash"

SRC_URI = " \
    file://ds1308_test.sh \
    file://eeprom_test.sh \
    file://emmc_test.sh \
    file://eth_test.sh \
    file://leds_keys_test.sh \
    file://m.2_ssd_test.sh \
    file://mipi1_test.sh \
    file://mipi2_test.sh \
    file://qspi_flash_test.sh \
    file://usb_scan_test.sh \
"

S = "${WORKDIR}"

do_install() {
    # ${bindir} is a standard Yocto variable that points to /usr/bin
    install -d ${D}${bindir}/test-scripts

    # Loop through all .sh files to install them at once
    for script in ${S}/*.sh; do
        install -m 0755 $script ${D}${bindir}/test-scripts/
    done
}

# Add the new path to the package
FILES:${PN} += "${bindir}/test-scripts/*"

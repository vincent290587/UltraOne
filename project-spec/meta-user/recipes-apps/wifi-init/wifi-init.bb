#
# This file is the wifi-init recipe.
#

SUMMARY = "WiFi initialization for Intel 3168NGW"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# 1. Source all required files
SRC_URI = " \
    file://wifi-start.service \
    file://iwlwifi-3168-29.ucode \
"

S = "${WORKDIR}"

# 2. Inherit systemd class for auto-enable functionality
inherit systemd

SYSTEMD_SERVICE:${PN} = "wifi-start.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {

    # Install the systemd service unit
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/wifi-start.service ${D}${systemd_system_unitdir}

    # Install the firmware file to /lib/firmware
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${S}/iwlwifi-3168-29.ucode ${D}${nonarch_base_libdir}/firmware/
}

# Ensure the files are included in the final package
FILES:${PN} = " \
    ${systemd_system_unitdir}/wifi-start.service \
    ${nonarch_base_libdir}/firmware/iwlwifi-3168-29.ucode \
"
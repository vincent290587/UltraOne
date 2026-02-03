FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://bsp.cfg"
KERNEL_FEATURES:append = " bsp.cfg"
SRC_URI += "file://user_2026-01-16-07-54-00.cfg \
            file://user_2026-01-29-09-47-00.cfg \
            file://user_2026-02-03-09-46-00.cfg \
            "


package com.rolandasprunskas.navigationsample.navigation

import android.os.Bundle

interface Screen {
    val navId: Int
    val bundle: Bundle?
}
package kr.co.lion.android01.my_project_loginservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //rootKey : Preference의 이름
        //xml파일에 있는 preference xml파일을 지정해준다
        setPreferencesFromResource(R.xml.pref, rootKey)
    }

}
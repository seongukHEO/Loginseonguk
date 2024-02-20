package kr.co.lion.android01.my_project_loginservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()
        return fragmentMainBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentMainBinding.apply {
            materialToolbar.apply {
                title = "FitnessManager"
                setNavigationIcon(R.drawable.finmyloggo_gh)
            }
        }

    }
    //이벤트 설정
    fun setEvent(){
       fragmentMainBinding.apply {
           //로그인 버튼을 눌렀을 떄
           logINbutton.setOnClickListener {
               mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, null)

           }
           //회원가입 버튼을 눌렀을 때
           newMemberButton.setOnClickListener {
               mainActivity.replaceFragment(FragmentName.LOGIN_FRAGMENT, true, true, null)

           }
           //아이디 찾기 버튼을 눌렀을 떄
           searchMainId.setOnClickListener {
               mainActivity.replaceFragment(FragmentName.SEARCH_ID_FRAGMENT, true ,true ,null)
           }
           //비밀번호 찾기 버튼을 눌렀을 때
           searchPWbutton.setOnClickListener {
               mainActivity.replaceFragment(FragmentName.SEARCH_PW_FRAGMENT, true ,true ,null)
           }
       }

    }
    //입력받기
    fun inputData(){
        fragmentMainBinding.apply {
            mainTextId.text.toString()
            mainTextPW.text.toString()
        }
    }
}












































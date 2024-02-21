package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentMainBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    //LoginClass의 객체
    lateinit var loginList:MutableList<LoginClass>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()
        return fragmentMainBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentMainBinding.apply {
            mainTextId.setText("")
            mainTextPW.setText("")
        }
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
               fragmentMainBinding.apply {
                   var id = mainTextId.text.toString()
                   var pw = mainTextPW.text.toString()
                   if (id.trim().isEmpty()){
                       enum.showDiaLog(mainActivity, "아이디 입력 오류", "아이디를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                           enum.showSoftInput(mainTextId, mainActivity)
                       }
                       return@apply
                   }
                   else if (pw.trim().isEmpty()){
                       enum.showDiaLog(mainActivity, "비밀번호 입력 오류", "비밀번호를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                           enum.showSoftInput(mainTextPW, mainActivity)
                       }
                       return@apply
                   }else{
                       inputData()
                   }
               }

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

            var id = mainTextId.text.toString()
            var loginId = LoginDAO.selectLoginOne(mainActivity, id)
            if (id != loginId.userId){
                enum.showDiaLog(mainActivity, "아이디 오류", "아이디를 확인해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(mainTextId, mainActivity)
                }
                return
            }

            var pw = mainTextPW.text.toString()
            if (pw != loginId.userPw){
                enum.showDiaLog(mainActivity, "비밀번호 오류", "비밀번호를 확인해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(mainTextPW, mainActivity)
                }
                return
            }
            var bundle = Bundle()
            bundle.putString("loginId", loginId.userId)
            mainActivity.replaceFragment(FragmentName.SHOW_FRAGMENT, true, true, bundle)
        }

    }
}












































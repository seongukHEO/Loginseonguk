package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentSearchPwBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class SearchPwFragment : Fragment() {

    lateinit var fragmentSearchPwBinding: FragmentSearchPwBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSearchPwBinding = FragmentSearchPwBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        buttonEvent()
        return fragmentSearchPwBinding.root
    }

    //툴바 설정
    fun setToolBar(){
        fragmentSearchPwBinding.apply {
            materialToolbar9.apply {
                title = "비밀번호 찾기"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭?
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SEARCH_PW_FRAGMENT)
                }
            }
        }
    }

    //버튼 이벤트
    fun buttonEvent(){
        fragmentSearchPwBinding.apply {
            searchPWButton.setOnClickListener {
                var searchpwid = searchIdText.text.toString()
                var searchpwNumber = textNumber.text.toString()

                if (searchpwid.trim().isEmpty()){
                    enum.showDiaLog(mainActivity, "아이디 입력 오류", "아이디를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(searchIdText, mainActivity)
                    }
                    return@setOnClickListener
                }
                else if (searchpwNumber.trim().isEmpty()){
                    enum.showDiaLog(mainActivity, "휴대폰 번호 입력 오류", "휴대폰 번호를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(textNumber, mainActivity)
                    }
                    return@setOnClickListener
                }else{
                    setEvent()
                }
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentSearchPwBinding.apply {
            var searchpwid = searchIdText.text.toString()
            var searchpwNumber = textNumber.text.toString().toInt()
            var userid = LoginDAO.selectLoginOne(mainActivity, searchpwid)

            if (searchpwid != userid?.userId || searchpwNumber != userid?.userNumber){
                enum.showDiaLog(mainActivity, "정보 오류", "입력하신 정보에 맞는 비밀번호가 없습니다"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(searchIdText, mainActivity)
                }
                return
            }
            searchPWtext2.text = "비밀번호 : ${userid?.userPw}"

        }
    }
}

















































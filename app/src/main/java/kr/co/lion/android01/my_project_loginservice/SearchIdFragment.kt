package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentSearchIdBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class SearchIdFragment : Fragment() {
    lateinit var fragmentSearchIdBinding: FragmentSearchIdBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSearchIdBinding = FragmentSearchIdBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        buttonSet()
        return fragmentSearchIdBinding.root
    }

    //툴바 설정
    fun setToolBar(){
        fragmentSearchIdBinding.apply {
            materialToolbar8.apply {
                title = "아이디 찾기"
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SEARCH_ID_FRAGMENT)
                }
            }
        }
    }
    fun buttonSet(){
        fragmentSearchIdBinding.apply {
            button2.setOnClickListener {
                var number1 = phonenumberText.text.toString()
                if (number1.trim().isEmpty()){
                    enum.showDiaLog(mainActivity, "휴대폰 번호 입력 오류", "휴대폰 번호를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(phonenumberText, mainActivity)
                    }
                    return@setOnClickListener
                }
                setEvent()
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentSearchIdBinding.apply {
            button2.setOnClickListener {

                var number = phonenumberText.text.toString().toInt()
                var searchId = LoginDAO.selectLoginOne1(mainActivity, number)
                if (number != searchId?.userNumber){
                    enum.showDiaLog(mainActivity, "저장된 정보 없음", "휴대폰 번호로 저장된 정보가 없습니다"){ dialogInterface: DialogInterface, i: Int ->
                        enum.showSoftInput(phonenumberText, mainActivity)
                    }
                    return@setOnClickListener
                }
                textView7.apply {
                    text = "아이디 : ${searchId?.userId}"
                }
            }


        }


    }
}


















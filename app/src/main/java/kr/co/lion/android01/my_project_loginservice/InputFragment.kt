package kr.co.lion.android01.my_project_loginservice

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.my_project_loginservice.databinding.FragmentInputBinding
import kr.co.lion.android01.newmemoproject_seonguk.enum

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        setEvent()
        return fragmentInputBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentInputBinding.apply {
            materialToolbar4.apply {
                title = "나의 정보 입력하기"
            }
        }
    }

    //이벤트 설정
    fun setEvent(){
        fragmentInputBinding.apply {
            infoInputButton.setOnClickListener {
                checkOK()
            }
        }
    }

    //정보 입력
    fun inputData(){
        fragmentInputBinding.apply {
            var height = heightText.text.toString().toInt()
            var weight = weightText.text.toString().toInt()
            var age = ageText.text.toString().toInt()
            var bmi = bmiText.text.toString().toInt()
            var bone = boneText.text.toString().toInt()

        }
    }

    //유효성 검사
    fun checkOK(){
        fragmentInputBinding.apply {
            var height = heightText.text.toString()
            if (height.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "키 입력 오류", "키를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(heightText, mainActivity)
                }
                return
            }

            var weight = weightText.text.toString()
            if (weight.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "몸무게 입력 오류", "몸무게를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(weightText, mainActivity)
                }
                return
            }

            var age = ageText.text.toString()
            if (age.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "나이 입력 오류", "나이를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(ageText, mainActivity)
                }
                return
            }

            var bmi = bmiText.text.toString()
            if (bmi.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "BMI 입력 오류", "BMI를 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(bmiText, mainActivity)
                }
                return
            }

            var bone = boneText.text.toString()
            if (bone.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "골격근량 입력 오류", "골격근량을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(boneText, mainActivity)
                }
                return
            }
        }
        inputData()
        mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)

    }
}



































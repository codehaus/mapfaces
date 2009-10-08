function decreaseTimePicker(timepicker, targetInputId){
    var minutes=timepicker.time.minute;
    if(minutes==0){minutes=59;}else{minutes-=1;}
    updateTimePicker(timepicker,minutes,targetInputId);
}

function increaseTimePicker(timepicker, targetInputId){
    var minutes=timepicker.time.minute;
    if(minutes==59){minutes=0;this.previousMinute=59;}else{minutes+=1;}
    updateTimePicker(timepicker,minutes,targetInputId);
}

function updateTimePicker(timepickerComp,minutes,datepickerId) {
    if (typeof timepickerComp != 'undefined') {
        timepickerComp.time.minute = minutes;
        timepickerComp.updateField();
        timepickerComp.moveHands();
        timepickerComp.fireEvent("onChange");
    }
}


function loadTimePicker(tpId, tpValueId, onTop, heure, minute) {
    var timepicker = new TimePicker(tpId, null, null, {imagesPath:"images", visible:true,offset:1000,
        startTime: {hour:heure, minute:minute}, previousMinute: minute,
        onChange:function(){
            if (this.time.hour < 12) var ampm = this.options.lang.am;
            else var ampm = this.options.lang.pm;
            var hour = this.time.hour%12;
            var hourinput = this.time.hour;
            if ((this.time.minute == 0) && (this.previousMinute != 0)) {
                if (this.previousMinute <= 30) {
                    this.time.hour--;
                } else if (30 < this.previousMinute) {
                    this.time.hour++;
                }
            }

            this.previousMinute = this.time.minute;

            this.updateAmPm();

            if (hour < 10) hour = "0"+hour;
            if (hour == 0 && ampm == this.options.lang.am) hour = "12";
            var minute = this.time.minute;
            if (minute < 10) minute = "0"+minute;
            if(!onTop && $(tpValueId))$(tpValueId).innerHTML=hour+":"+minute+" "+ampm;
            else {
                var inputvalue = $(tpValueId).value+'T';
                var substr = inputvalue.substring(0, inputvalue.indexOf('T'));

                if($(tpValueId)){
                    $(tpValueId).innerHTML = '';
                    $(tpValueId).innerHTML = substr+'T'+hourinput+':'+minute+':'+new Date().getSeconds()+'+01' ;
                }
            }
        }
    });
    return timepicker;
}
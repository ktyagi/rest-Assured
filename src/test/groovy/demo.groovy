
def key = 'Key3'
def aMap = [
        'Key1': 'Value1', // Put key1 -> Value 1 to the map
        Key2  : 'Value2', // You can also skip the quotes, the key will automatically be a String
        (key) : 'Another value' // If you want the key to be the value of a variable, you need to put it in parantheses
]
//    aMap.each { key1, value1 ->
//        println key1
//        println value1
//    }

//aMap.each {
//    println it.key
//    println it.value
//}

//return ArrayList of Map entries
println (aMap.collect { return it })  //[Key1=Value1, Key2=Value2, Key3=Another value]
//return Map
println( aMap.collectEntries { return it }) //[Key1:Value1, Key2:Value2, Key3:Another value]

println(aMap.collectEntries { key1, value1 ->
    return [(key1): value1]
})

println(aMap.collectEntries { key1, value ->
    def newKey = key1 + "^2"
    return [(key1): value, (newKey): value+value]
})



node('master') {

//    currentBuild.displayName = "Load FPP"
    currentBuild.description = "system=${system} day=${day} subday=${subday} "

    def system="${params.system}".toLowerCase().trim()
    def day="${params.day}".trim()
    def subday="${params.subday}".trim()

//    properties([pipelineTriggers([cron('0 4 * * * ')])])

//    properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')),
//                parameters([choice(choices: ['devi', 'tsta', 'tstb', 'stag', 'devsprtdsb001', 'devsprtdsb002', 'devsprtdsb020'],
//                                    description: 'First entry is treated as default value. If user wants to run it automatically with different default value then click on configure button in left pane and make that value as first entry in Choices text box.', name: 'system'),
//                            choice(choices: ['0', '1'],
//                                    description: 'First entry is treated as default value. If user wants to run it automatically with different default value then click on configure button in left pane and make that value as first entry in Choices text box.', name: 'day'),
//                            choice(choices: ['0', '1', '2', 'ALL'],
//                                    description: 'First entry is treated as default value. If user wants to run it automatically with different default value then click on configure button in left pane and make that value as first entry in Choices text box.', name: 'subday')]),
//                pipelineTriggers([cron('0 4 * * * ')])])


    if(system.length()== 4) {
        env.system= system + 'prtisa001'
    }
    println 'System  : '  + "${env.system}"

    stage('checkout and run') {
        svn 'http://subversion/svn/integration/trunk/load_fpp_obet_files'
        sh 'rm -rf $(pwd)/data_fpp_obet_jetbet-in/*.xml'
        sh 'PERL5LIB=$(pwd)/cpanlib/lib/perl5:$PERL5LIB system=${system} day=${day} subday=${subday} bash load_fpp_obet_files.sh'
    }
    stage('copy xmls') {
        sh 'scp -r $(pwd)/data_fpp_obet_jetbet-in/ svc_jenkins_install@sdlc.nzrb.local@${system}.sdlc.nzrb.local:~'
        sh '/bin/ssh -24t -o PasswordAuthentication=no -o StrictHostKeyChecking=no -i /var/lib/jenkins/.ssh/id_rsa svc_jenkins_install@sdlc@${system}.sdlc.nzrb.local bash data_fpp_obet_jetbet-in/copyxmls.sh'
    }

}





def releases = []
("/mnt/source/IS/application/" as File).eachFile groovy.io.FileType.FILES, {
    def finds = (it.name =~ /is-(.*).tar.gz/)
    if (finds.count==1)
    {
        releases << finds[0][1]
    }
}
def result = releases.sort{ a,b -> b <=>a }



Map m =[ james  :"silly boy",
         janny  :"crazy girl",
         jimmy  :"Funny man",
         georges:"massive fella" ]
Map sorted = m.sort { a, b -> a.value <=> b.value }
println sorted
sorted = m.sort { a, b -> a.value.toLowerCase() <=> b.value.toLowerCase() }
println sorted





























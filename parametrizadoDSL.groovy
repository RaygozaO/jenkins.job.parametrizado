job('ejemplo2-job-dsl'){
  description('Job DSL de ejejmplo para el curso de Jenkins')
  scm {
    git('https://github.com/RaygozaO/jenkins.job.parametrizado.git', 'main'){ node ->
      node / gitConfigName('RaygozaO')
      node / gitConfigEmail('oscar.raygoza@gmail.com')
    }
  }
  parameters {
    stringParam('nombre',defaultValue = 'Oscar', description = 'Parametro de cadena para el job Booleano')
    choiceParam('planeta',['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno','Urano','Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron ('H/7 * * * *')
  }
  steps {
    shell ("bash jobscript.sh")
  }
  publishers {
    mailer ('oscar.raygoza@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  } 
}

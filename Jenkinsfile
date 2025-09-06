pipeline 
{
    agent any
       stages 
    {
        stage("Build") 
        {
            steps
            {
                echo("build the project")
            }
        }    
        
        stage("Run unite test") 
        {
            steps
            {
                echo("run unit tests")
            }
        }  
        
        stage("Run Integration test") 
        {
            steps
            {
                echo("run integration tests")
            }
        }   
        
        stage("Deploy to Dev environment") 
        {
            steps
            {
                echo("deploy to Dev")
            }
        }  
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        
        stage("Run regression test cases on QA"){
            steps{
                echo("Run test cases on QA")
            }
        }
        
        stage("Deploy to stage environment "){
            steps{
                echo("deploy to stage done")
            }
        }
        
        stage("Run sanity test cases on Stage "){
            steps{
                echo("run sanity tests on stage")
            }
        }
        
        stage("Deploy to UAT environment "){
            steps{
                echo("deploy to UAT done")
            }
        }
        
        stage("Run sanity test cases on UAT "){
            steps{
                echo("run sanity tests on UAT")
            }
        }
   
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
       
    }
}
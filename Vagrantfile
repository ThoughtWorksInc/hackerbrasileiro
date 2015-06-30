Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu-14.04"
  config.vm.box_url = "http://files.vagrantup.com/precise64.box"

  config.vm.define "hackerbrasileiro" do |hb|
  end

  config.vm.network "forwarded_port", guest: 8080, host: 8088

  config.vm.provider "virtualbox" do |box|
    box.name = "hackerbrasileiro"
    box.memory = 2048
  end

  config.vm.provision :shell, :path => "infrastructure/scripts/local/git.sh"
  config.vm.provision :shell, :path => "infrastructure/scripts/local/openjdk.sh"
  config.vm.provision :shell, :path => "infrastructure/scripts/local/facemorpher.sh"
  config.vm.provision :shell, :path => "infrastructure/scripts/local/app.sh"
end
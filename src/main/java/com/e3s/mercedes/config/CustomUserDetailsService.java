package com.e3s.mercedes.config;

import com.e3s.mercedes.entity.Utilisateur;
import com.e3s.mercedes.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
        return new User(
                user.getEmail(),
                user.getMotDePasse(),
                java.util.List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}